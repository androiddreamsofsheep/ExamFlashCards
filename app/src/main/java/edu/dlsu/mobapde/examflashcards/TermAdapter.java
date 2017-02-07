package edu.dlsu.mobapde.examflashcards;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by courtneyngo on 06/02/2017.
 */
public class TermAdapter extends RecyclerView.Adapter<TermAdapter.TermHolder> {

    private ArrayList<Term> terms;
    private OnTermClickListener onTermClickListener;
    private OnTermLongClickListener onTermLongClickListener;

    public TermAdapter(ArrayList<Term> terms){
        this.terms = terms;
    }

    @Override
    public TermHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_term, parent, false);
        return new TermHolder(view);
    }

    @Override
    public void onBindViewHolder(TermHolder holder, int position) {
        Term term = terms.get(position);
        term.setLayoutPosition(position);

        holder.tvTerm.setText(term.getTerm());
        holder.container.setTag(term);

        if(position % 2 == 0){
            holder.container.setBackgroundColor(Color.parseColor("#eeeeee"));
        } else {
            holder.container.setBackgroundColor(Color.parseColor("#e0e0e0"));
        }

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onTermClickListener != null){
                    onTermClickListener.onTermClick(v, (Term)v.getTag());
                }
            }
        });

        holder.container.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(onTermLongClickListener != null) {
                    onTermLongClickListener.onTermLongClick(v, (Term) v.getTag());
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return terms.size();
    }

    public class TermHolder extends RecyclerView.ViewHolder{
        TextView tvTerm;
        View container;

        public TermHolder(View itemView) {
            super(itemView);
            tvTerm = (TextView) itemView.findViewById(R.id.tv_term);
            container = itemView.findViewById(R.id.container);
        }
    }

    public void addTerm(Term t){
        terms.add(t);
        notifyItemInserted(terms.size());
    }

    public void editTerm(Term t){
        terms.set(t.getLayoutPosition(), t);
        notifyItemChanged(t.getLayoutPosition());
    }

    public interface OnTermClickListener{
        public void onTermClick(View view,  Term t);
    }

    public interface OnTermLongClickListener{
        public void onTermLongClick(View view, Term t);
    }

    public OnTermClickListener getOnTermClickListener() {
        return onTermClickListener;
    }

    public void setOnTermClickListener(OnTermClickListener onTermClickListener) {
        this.onTermClickListener = onTermClickListener;
    }

    public OnTermLongClickListener getOnTermLongClickListener() {
        return onTermLongClickListener;
    }

    public void setOnTermLongClickListener(OnTermLongClickListener onTermLongClickListener) {
        this.onTermLongClickListener = onTermLongClickListener;
    }
}
