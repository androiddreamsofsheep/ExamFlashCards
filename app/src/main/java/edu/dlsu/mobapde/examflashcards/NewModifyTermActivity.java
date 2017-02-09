package edu.dlsu.mobapde.examflashcards;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class NewModifyTermActivity extends AppCompatActivity {

    EditText etTerm;
    EditText etDefinition;
    ImageView ivDone;

    Term term;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_term);

        etTerm = (EditText) findViewById(R.id.et_term);
        etDefinition = (EditText) findViewById(R.id.et_definition);
        ivDone = (ImageView) findViewById(R.id.iv_done);

        term = getIntent().getParcelableExtra(MainActivity.EXTRA_TERM);

        if(term != null){
            etTerm.setText(term.getTerm());
            etDefinition.setText(term.getDescription());
        }

        ivDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputTerm = etTerm.getText().toString().trim();
                String inputDefinition = etDefinition.getText().toString().trim();

                if(inputTerm.isEmpty() || inputDefinition.isEmpty()){
                    Toast.makeText(getBaseContext(), "Please enter a valid term and definition.", Toast.LENGTH_SHORT).show();
                }else{
                    Intent data = new Intent();

                    if(term == null){
                        term = new Term();
                    }
                    term.setTerm(inputTerm);
                    term.setDescription(inputDefinition);

                    data.putExtra(MainActivity.EXTRA_TERM, term);
                    setResult(RESULT_OK, data);
                    finish();
                }
            }
        });
    }
}
