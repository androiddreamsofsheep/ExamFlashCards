package edu.dlsu.mobapde.examflashcards;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_TERM = "term";
    public static final int REQUEST_ADD = 0;
    public static final int REQUEST_EDIT = 1;

    RecyclerView rvTerms;
    TextView tvTerm;
    TextView tvDefinition;
    ImageView ivAdd;
    View container;

    ArrayList<Term> terms;
    TermAdapter termAdapter;

    Term currentSelectedTerm = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvTerms = (RecyclerView) findViewById(R.id.rv_terms);
        tvTerm = (TextView) findViewById(R.id.tv_term);
        tvDefinition = (TextView) findViewById(R.id.tv_definition);
        ivAdd = (ImageView) findViewById(R.id.iv_add);
        container = findViewById(R.id.container);

        ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), NewModifyTermActivity.class);
                startActivityForResult(intent, REQUEST_ADD);
            }
        });

        container.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(currentSelectedTerm!=null) {
                    Intent intent = new Intent(getBaseContext(), NewModifyTermActivity.class);
                    intent.putExtra(EXTRA_TERM, currentSelectedTerm);
                    startActivityForResult(intent, REQUEST_EDIT);
                }
                return false;
            }
        });

        prepareData();

        termAdapter = new TermAdapter(terms);

        termAdapter.setOnTermClickListener(new TermAdapter.OnTermClickListener() {
            @Override
            public void onTermClick(View view, Term t) {
                tvTerm.setText(t.getTerm());
                tvDefinition.setText(t.getDefinition());

                currentSelectedTerm = t;
            }
        });

        termAdapter.setOnTermLongClickListener(new TermAdapter.OnTermLongClickListener() {
            @Override
            public void onTermLongClick(View view, Term t) {
                Term term = new Term(tvTerm.getText().toString(), tvDefinition.getText().toString());
                term.setLayoutPosition(t.getLayoutPosition());

                Intent intent = new Intent(getBaseContext(), NewModifyTermActivity.class);
                intent.putExtra(EXTRA_TERM, term);
                startActivityForResult(intent, REQUEST_EDIT);
            }
        });

        rvTerms.setAdapter(termAdapter);
        rvTerms.setLayoutManager(new LinearLayoutManager(getBaseContext(), LinearLayoutManager.HORIZONTAL, false));

    }

    public void prepareData(){
        terms = new ArrayList<>();
        terms.add(new Term("Term 1", "asdasdasdsadasdasds"));
        terms.add(new Term("Term 2", "asdasdasdsadasdasds"));
        terms.add(new Term("Term 3", "asdasdasdsadasdasds"));
        terms.add(new Term("Term 4", "asdasdasdsadasdasds"));
        terms.add(new Term("Term 5", "asdasdasdsadasdasds"));
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if(resultCode == RESULT_OK){
            if(requestCode == REQUEST_EDIT){
                Term term = data.getParcelableExtra(EXTRA_TERM);
                termAdapter.editTerm(term);

                currentSelectedTerm = term;
                displayData(term);
            }else if (requestCode == REQUEST_ADD){
                Term term = data.getParcelableExtra(EXTRA_TERM);
                termAdapter.addTerm(term);

                currentSelectedTerm = term;
                displayData(term);
            }
        }

    }

    public void displayData(Term term){
        tvTerm.setText(term.getTerm());
        tvDefinition.setText(term.getDefinition());
    }
}
