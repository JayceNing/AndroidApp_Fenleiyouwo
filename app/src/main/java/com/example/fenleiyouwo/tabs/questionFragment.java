package com.example.fenleiyouwo.tabs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.fenleiyouwo.R;

public class questionFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_question, container, false);
        QuestionAdapter questionAdapter = new QuestionAdapter(this.getContext(), R.layout.question_item, questionI.getAllQuestions());

        ListView listView = (ListView) root.findViewById(R.id.question_listView);

        listView.setAdapter(questionAdapter);

        return root;
    }

}
