package com.kotlinquiz.app.ui.testFragment


import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kotlinquiz.app.R
import com.architectcoders.domain.test.TestQuestion
import com.kotlinquiz.app.databinding.ViewHolderTestBinding
import com.kotlinquiz.app.ui.common.basicDiffUtil
import com.kotlinquiz.app.ui.common.inflate

class TestSolidAdapter(private val interaction: Interaction? = null) :
    RecyclerView.Adapter<TestSolidAdapter.ViewHolder>() {


    var questions: List<TestQuestion> by basicDiffUtil(
        emptyList(),
        areItemsTheSame = { old, new -> old.uniqueId == new.uniqueId }
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            parent.inflate(R.layout.view_holder_test, false),
            interaction
        )


    override fun getItemCount(): Int = questions.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val question = questions[position]
        holder.bind(question)
    }

    override fun getItemId(position: Int): Long {
        return questions[position].uniqueId.toLong()
    }


    class ViewHolder(view: View, private val interaction: Interaction?) :
        RecyclerView.ViewHolder(view) {

        private val viewBinding = ViewHolderTestBinding.bind(view)

        fun bind(question: TestQuestion) {
            with(viewBinding) {
                textQuestion.text = question.text


                setRadios(question.answer)

                if (interaction != null) {
                    checkYes.setOnClickListener {
                        interaction.onItemSelected(adapterPosition, "yes")
                    }
                    checkNo.setOnClickListener {
                        interaction.onItemSelected(adapterPosition, "no")
                    }
                }

            }
        }

        private fun setRadios(answer: String) {

            with(viewBinding) {

                rdGroup.clearCheck()

                if (answer == "") return
                when (answer) {
                    "yes" -> checkYes.isChecked = true
                    "no" -> checkNo.isChecked = true
                }
            }
        }
    }

    interface Interaction {
        fun onItemSelected(position: Int, selection: String)
    }

}
