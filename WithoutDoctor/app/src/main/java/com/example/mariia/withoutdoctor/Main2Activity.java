package com.example.mariia.withoutdoctor;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class Main2Activity extends Activity {

    Map<String, String> news;

    protected void getInformation(){
        news = new HashMap<String, String>();
        news.put("Рыжик", "Ры́жик — название группы видов грибов рода Млечник (Lactarius). " +
                "Слово „рыжик“ заимствовано некоторыми неславянскими языками, например немецким (нем. Reizker) " +
                "и венгерским (венг. rizike).");
        news.put("Барсик", "Олимпиада ежегодно входит в Перечень олимпиад РСОШ, дающих льготы при поступлении " +
                "в вузы - в Перечнь олимпиад школьников и их уровней на 2017/18 учебный год вошла под номером 18, " +
                "уровень 2 (приказ Минобрнауки РФ от 30.08.2017 № 866). Кроме того, Интернет-олимпиада школьников" +
                " по физике получила статус международной - см. Распоряжение Минобрнауки от 17 марта 2017 г. № Р-130");
        news.put("Мурзик", "Существительное, одушевлённое, мужской род, 2-е склонение " +
                "(тип склонения 3a по классификации ");
        news.put("Мурка", "Сотрудница внутренних органов Мария Климова направлена в Одессу усмирять тамошнюю разгулявшуюся " +
                "преступность, внедрившись под прикрытием в местную банду одного из самых крупных криминальных авторитетов — Бриллианта. " +
                "Теперь она обычная воровка по кличке Мурка. При этом Мария так хорошо вживается в эту роль, что вскоре " +
                "приобретает большое уважение со стороны своих новых «коллег» и становится прототипом Мурки из знаменитой песни…");
        news.put("Васька", "рыжий, полосаый и усатый");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getInformation();
        setContentView(R.layout.activity_main2);
        String s= getIntent().getStringExtra("<StringName>");
        TextView title = (TextView)findViewById(R.id.title);
        title.setText(s);
        TextView content=(TextView) findViewById(R.id.content);
        content.setText(news.get(s));
        Button button = (Button)findViewById(R.id.returnButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent nextActivity=new Intent(Main2Activity.this, MainActivity.class);
                startActivity(nextActivity);
            }
        });
    }
}
