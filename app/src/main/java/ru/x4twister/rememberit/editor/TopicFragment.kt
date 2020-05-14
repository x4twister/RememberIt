/*
 * Copyright (c) 2020 x4twister
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */

package ru.x4twister.rememberit.editor

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.x4twister.rememberit.model.Question
import ru.x4twister.rememberit.R
import ru.x4twister.rememberit.model.Topic
import ru.x4twister.rememberit.model.TopicLab
import ru.x4twister.rememberit.databinding.FragmentTopicBinding
import ru.x4twister.rememberit.databinding.ListItemQuestionBinding

class TopicFragment: Fragment() {

    interface Callback{
        fun onTopicDeleted()
    }

    val callback: Callback by lazy {
        activity as Callback
    }

    private var topicId:String=""

    private val topic: Topic by lazy {
        TopicLab.getTopic(topicId)!!
    }

    private val topicViewModel by lazy {
        EditorViewModel(topic,object: EditorViewModel.Callback{
            override fun onQuestionAdded(currentQuestion: Question) {
                onQuestionEdited(currentQuestion)
            }

            // TODO как использовать здесь topicViewModel?
            override fun onQuestionEdited(currentQuestion: Question) {
                showDialog(currentQuestion.answer,"Answer", REQUEST_ANSWER)
                showDialog(currentQuestion.subject,"Subject", REQUEST_SUBJECT)
            }

            override fun onQuestionDeleted() {
                updateUI()
            }
        })
    }

    private val questionAdapter by lazy {
        QuestionAdapter(topic.questions)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        topicId=arguments!!.getSerializable(ARG_TOPIC_ID) as String

        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentTopicBinding =DataBindingUtil
            .inflate(inflater, R.layout.fragment_topic,container,false)

        activity?.title=topic.name

        binding.viewModel=topicViewModel

        binding.recycleView.run {
            layoutManager= LinearLayoutManager(activity)
            adapter=questionAdapter

            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.fragment_topic,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.title){
            "Edit" -> {
                topicViewModel.editMode=topicViewModel.editMode.not()
                true
            }
            "Info" -> {
                val intent= InfoActivity.newIntent(context!!,topic.id)
                context!!.startActivity(intent)
                true
            }
            "Rename topic" -> {
                showDialog(topic.name, "name", REQUEST_TEXT)
                true
            }
            "Swap questions" -> {
                topic.swapQuestions()
                topicViewModel.notifyChange()
                updateUI()
                true
            }
            "Delete topic" -> {
                showDialog("", "Enter '${topic.name}' for delete", REQUEST_DELETE)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showDialog(default: String, title: String, type: Int) {
        val dialog = EditTextFragment.newInstance(default, title)
        dialog.setTargetFragment(this, type)
        dialog.show(fragmentManager!!, DIALOG_TEXT)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode!=Activity.RESULT_OK)
            return

        val result = EditTextFragment.getValue(data)

        when (requestCode){
            REQUEST_TEXT -> {
                topic.name= result
                topicViewModel.notifyChange()
            }

            REQUEST_SUBJECT -> {
                topicViewModel.currentQuestion!!.subject= result
                topicViewModel.notifyChange()
                updateUI()
            }

            REQUEST_ANSWER -> {
                topicViewModel.currentQuestion!!.answer= result
                topicViewModel.notifyChange()
            }

            REQUEST_DELETE -> {
                if (topic.name==result) {
                    TopicLab.deleteTopic(topic)
                    callback.onTopicDeleted()
                }
            }
        }
    }

    private fun updateUI() {
        questionAdapter.run {
            setQuestions(topic.questions)
            notifyDataSetChanged()
        }
    }

    companion object{

        const val ARG_TOPIC_ID="topic_id"
        const val DIALOG_TEXT="DialogText"
        const val REQUEST_TEXT=0
        const val REQUEST_SUBJECT=1
        const val REQUEST_ANSWER=2
        const val REQUEST_DELETE=3

        fun newInstance(topicId: String): TopicFragment {
            val args=Bundle()
            args.putSerializable(ARG_TOPIC_ID,topicId)

            val fragment=TopicFragment()
            fragment.arguments=args
            return fragment
        }
    }

    inner class QuestionHolder(private val binding: ListItemQuestionBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(question: Question) {
            binding.viewModel!!.question=question
        }

        init {
            binding.viewModel=QuestionViewModel(object :QuestionViewModel.Callback{
                override fun questionClicked(question: Question) {
                    topicViewModel.currentQuestion=question
                }
            })
        }
    }

    inner class QuestionAdapter(private var questions: List<Question>): RecyclerView.Adapter<QuestionHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionHolder {
            val inflater=LayoutInflater.from(activity)
            val binding:ListItemQuestionBinding=DataBindingUtil
                .inflate(inflater,R.layout.list_item_question,parent,false)

            return QuestionHolder(binding)
        }

        override fun getItemCount()=questions.size

        override fun onBindViewHolder(holder: QuestionHolder, position: Int) {
            holder.bind(questions[position])
        }

        fun setQuestions(newQuestions: List<Question>){
            questions=newQuestions
        }
    }
}