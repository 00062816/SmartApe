package bigdreams.example.alexbig.smartape.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import bigdreams.example.alexbig.smartape.R;
import bigdreams.example.alexbig.smartape.models.Answer;

import java.util.List;

public abstract class AnswerAdapter extends RecyclerView.Adapter<AnswerAdapter.AnswerViewHolder>{

    private Context context;
    private List<Answer> answerList;

    public AnswerAdapter(Context context){
        this.context = context;
    }

    public void setAnswerList(List<Answer> answerList){
        this.answerList = answerList;
        this.notifyDataSetChanged();
    }

    public List<Answer> getAnswerList(){
        return answerList;
    }

    @NonNull
    @Override
    public AnswerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_created_answer_multiple_choice, parent, false);
        return new AnswerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnswerViewHolder holder, int position) {
        final Answer answer = answerList.get(position);

        if (answer.isCorrect()){
            holder.checkBox.setChecked(true);
        }else{
            holder.checkBox.setChecked(false);
        }
        holder.checkBox.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (holder.checkBox.isChecked()){
                    answer.setCorrect(true);
                }else{
                    answer.setCorrect(false);
                }
            }
        });

        holder.numberTextView.setText(""+(position+1));
        holder.answerTextView.setText(answer.getText());

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                AnswerAdapter.this.onItemClick(view, position);
            }
        });
    }

    public abstract void onItemClick(View v, int position);

    @Override
    public int getItemCount() {
        return answerList.size();
    }

    public class AnswerViewHolder extends RecyclerView.ViewHolder{

        private TextView numberTextView;
        private TextView answerTextView;
        private CheckBox checkBox;

        public AnswerViewHolder(View itemView) {
            super(itemView);
            numberTextView = itemView.findViewById(R.id.textView_recyclerCreateAnswer_answerNumber);
            answerTextView = itemView.findViewById(R.id.editText_createAnswer_answer);
            checkBox = itemView.findViewById(R.id.checkBox_createAnswer_answerCorrect);
        }
    }
}
