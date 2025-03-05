package com.example.recyclerviewdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        List<Items> items = new ArrayList<>();

        items.add(new Items("Shelby","Thomas","Artificial Intelligence (AI) stands at the forefront of technological advancements, transforming the way we live, work, and interact with the world. Its rapid evolution has sparked both excitement and apprehension, raising questions about the profound implications it holds for the future of humanity. In this exploration, we will delve into the multifaceted landscape of AI, examining its current state, potential applications, ethical considerations, and the transformative journey it promises to take us on.\n" +
                "\n" +
                "Current State of AI:\n" +
                "\n" +
                "As of now, AI has made remarkable strides, with machine learning algorithms demonstrating prowess in tasks ranging from image recognition and natural language processing to complex problem-solving. Neural networks, inspired by the human brain, have become the backbone of many AI systems, allowing machines to learn and adapt based on data patterns. The integration of AI into various industries, from healthcare and finance to manufacturing and entertainment, underscores its versatility and potential impact on global economies.\n" +
                "\n" +
                "Applications of AI:\n" +
                "\n" +
                "The applications of AI are diverse and continually expanding. In healthcare, AI is revolutionizing diagnostics and personalized medicine, analyzing vast datasets to identify patterns and predict patient outcomes. In autonomous vehicles, AI algorithms navigate complex environments, enhancing safety and efficiency. Moreover, AI is reshaping education, finance, and customer service, streamlining processes and augmenting human capabilities. The fusion of AI with robotics holds the promise of transforming industries like manufacturing and logistics, paving the way for unprecedented levels of automation.","2024.01.20",R.drawable.thomasshelby));

        items.add(new Items("Shelby","Ada","Artificial Intelligence (AI) stands at the forefront of technological advancements, transforming the way we live, work, and interact with the world. Its rapid evolution has sparked both excitement and apprehension, raising questions about the profound implications it holds for the future of humanity. In this exploration, we will delve into the multifaceted landscape of AI, examining its current state, potential applications, ethical considerations, and the transformative journey it promises to take us on.\n" +
                "\n" +
                "Current State of AI:\n" +
                "\n" +
                "As of now, AI has made remarkable strides, with machine learning algorithms demonstrating prowess in tasks ranging from image recognition and natural language processing to complex problem-solving. Neural networks, inspired by the human brain, have become the backbone of many AI systems, allowing machines to learn and adapt based on data patterns. The integration of AI into various industries, from healthcare and finance to manufacturing and entertainment, underscores its versatility and potential impact on global economies.\n" +
                "\n" +
                "Applications of AI:\n" +
                "\n" +
                "The applications of AI are diverse and continually expanding. In healthcare, AI is revolutionizing diagnostics and personalized medicine, analyzing vast datasets to identify patterns and predict patient outcomes. In autonomous vehicles, AI algorithms navigate complex environments, enhancing safety and efficiency. Moreover, AI is reshaping education, finance, and customer service, streamlining processes and augmenting human capabilities. The fusion of AI with robotics holds the promise of transforming industries like manufacturing and logistics, paving the way for unprecedented levels of automation.","2024.01.20",R.drawable.adashelby));

        items.add(new Items("Shelby","Arthur","a jewish Man","2024.01.20",R.drawable.arthurshelby));

        items.add(new Items("Shelby","Grace","Artificial Intelligence (AI) stands at the forefront of technological advancements, transforming the way we live, work, and interact with the world. Its rapid evolution has sparked both excitement and apprehension, raising questions about the profound implications it holds for the future of humanity. In this exploration, we will delve into the multifaceted landscape of AI, examining its current state, potential applications, ethical considerations, and the transformative journey it promises to take us on.\n" +
                "\n" +
                "Current State of AI:\n" +
                "\n" +
                "As of now, AI has made remarkable strides, with machine learning algorithms demonstrating prowess in tasks ranging from image recognition and natural language processing to complex problem-solving. Neural networks, inspired by the human brain, have become the backbone of many AI systems, allowing machines to learn and adapt based on data patterns. The integration of AI into various industries, from healthcare and finance to manufacturing and entertainment, underscores its versatility and potential impact on global economies.\n" +
                "\n" +
                "Applications of AI:\n" +
                "\n" +
                "The applications of AI are diverse and continually expanding. In healthcare, AI is revolutionizing diagnostics and personalized medicine, analyzing vast datasets to identify patterns and predict patient outcomes. In autonomous vehicles, AI algorithms navigate complex environments, enhancing safety and efficiency. Moreover, AI is reshaping education, finance, and customer service, streamlining processes and augmenting human capabilities. The fusion of AI with robotics holds the promise of transforming industries like manufacturing and logistics, paving the way for unprecedented levels of automation.","2024.01.20",R.drawable.graceshelby));
        items.add(new Items("Shelby","John","Artificial Intelligence (AI) stands at the forefront of technological advancements, transforming the way we live, work, and interact with the world. Its rapid evolution has sparked both excitement and apprehension, raising questions about the profound implications it holds for the future of humanity. In this exploration, we will delve into the multifaceted landscape of AI, examining its current state, potential applications, ethical considerations, and the transformative journey it promises to take us on.\n" +
                "\n" +
                "Current State of AI:\n" +
                "\n" +
                "As of now, AI has made remarkable strides, with machine learning algorithms demonstrating prowess in tasks ranging from image recognition and natural language processing to complex problem-solving. Neural networks, inspired by the human brain, have become the backbone of many AI systems, allowing machines to learn and adapt based on data patterns. The integration of AI into various industries, from healthcare and finance to manufacturing and entertainment, underscores its versatility and potential impact on global economies.\n" +
                "\n" +
                "Applications of AI:\n" +
                "\n" +
                "The applications of AI are diverse and continually expanding. In healthcare, AI is revolutionizing diagnostics and personalized medicine, analyzing vast datasets to identify patterns and predict patient outcomes. In autonomous vehicles, AI algorithms navigate complex environments, enhancing safety and efficiency. Moreover, AI is reshaping education, finance, and customer service, streamlining processes and augmenting human capabilities. The fusion of AI with robotics holds the promise of transforming industries like manufacturing and logistics, paving the way for unprecedented levels of automation.","2024.01.20",R.drawable.johnshelby));
        items.add(new Items("Shelby","Lizzie","Artificial Intelligence (AI) stands at the forefront of technological advancements, transforming the way we live, work, and interact with the world. Its rapid evolution has sparked both excitement and apprehension, raising questions about the profound implications it holds for the future of humanity. In this exploration, we will delve into the multifaceted landscape of AI, examining its current state, potential applications, ethical considerations, and the transformative journey it promises to take us on.\n" +
                "\n" +
                "Current State of AI:\n" +
                "\n" +
                "As of now, AI has made remarkable strides, with machine learning algorithms demonstrating prowess in tasks ranging from image recognition and natural language processing to complex problem-solving. Neural networks, inspired by the human brain, have become the backbone of many AI systems, allowing machines to learn and adapt based on data patterns. The integration of AI into various industries, from healthcare and finance to manufacturing and entertainment, underscores its versatility and potential impact on global economies.\n" +
                "\n" +
                "Applications of AI:\n" +
                "\n" +
                "The applications of AI are diverse and continually expanding. In healthcare, AI is revolutionizing diagnostics and personalized medicine, analyzing vast datasets to identify patterns and predict patient outcomes. In autonomous vehicles, AI algorithms navigate complex environments, enhancing safety and efficiency. Moreover, AI is reshaping education, finance, and customer service, streamlining processes and augmenting human capabilities. The fusion of AI with robotics holds the promise of transforming industries like manufacturing and logistics, paving the way for unprecedented levels of automation.","2024.01.20",R.drawable.lizzieshelby));
        items.add(new Items("Gray","Michael","Artificial Intelligence (AI) stands at the forefront of technological advancements, transforming the way we live, work, and interact with the world. Its rapid evolution has sparked both excitement and apprehension, raising questions about the profound implications it holds for the future of humanity. In this exploration, we will delve into the multifaceted landscape of AI, examining its current state, potential applications, ethical considerations, and the transformative journey it promises to take us on.\n" +
                "\n" +
                "Current State of AI:\n" +
                "\n" +
                "As of now, AI has made remarkable strides, with machine learning algorithms demonstrating prowess in tasks ranging from image recognition and natural language processing to complex problem-solving. Neural networks, inspired by the human brain, have become the backbone of many AI systems, allowing machines to learn and adapt based on data patterns. The integration of AI into various industries, from healthcare and finance to manufacturing and entertainment, underscores its versatility and potential impact on global economies.\n" +
                "\n" +
                "Applications of AI:\n" +
                "\n" +
                "The applications of AI are diverse and continually expanding. In healthcare, AI is revolutionizing diagnostics and personalized medicine, analyzing vast datasets to identify patterns and predict patient outcomes. In autonomous vehicles, AI algorithms navigate complex environments, enhancing safety and efficiency. Moreover, AI is reshaping education, finance, and customer service, streamlining processes and augmenting human capabilities. The fusion of AI with robotics holds the promise of transforming industries like manufacturing and logistics, paving the way for unprecedented levels of automation.","2024.01.20",R.drawable.michaelgray));



        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter(getApplicationContext(),items));
    }
}