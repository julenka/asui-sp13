package homework4.android.app;


import homework4.android.R;
import homework4.android.test.TestAllObjects;
import homework4.android.test.TestAllTests;
import homework4.android.test.TestFilledRect;
import homework4.android.test.TestHomework2;
import homework4.android.test.TestHomework3;
import homework4.android.test.TestInteractiveWindowGroup;
import homework4.android.test.TestLayoutGroup;
import homework4.android.test.TestNewRectBehavior;
import homework4.android.test.TestOutlineRect;
import homework4.android.test.TestScaledGroup;
import homework4.android.test.TestSimpleGroup;
import homework4.android.test.TestTestFrame;
import homework4.android.test.TestWindowGroup;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Homework4MainActivity extends Activity {
    /** Called when the activity is first created. */
	private LinearLayout root;
	
	private Context context;   
	
	private void addTestButton(final Class<?> cls)
	{
		addTestButton(cls.getSimpleName(), cls);
	}
	
	private void addTestButton(String text, final Class<?> cls )
	{
		Button addMe = new Button(this);
		addMe.setText(text);
		
		addMe.setOnClickListener(new OnClickListener(){
        	public void onClick(View v){        		
       		 Intent i = new Intent(context, cls);                    
       	     startActivity(i);
       	}});
		root.addView(addMe);
	}
	
	private void addText(String text)
	{
		TextView addMe = new TextView(this);
		addMe.setText(text);
		root.addView(addMe);
	}
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        context = this;
        root = (LinearLayout) findViewById(R.id.root);
        addText("Homework 4 Tests");
        
        addText("Homework 3 Tests");
        addTestButton(TestHomework3.class);
        addTestButton(DrawingEditor.class);
        addTestButton(TestNewRectBehavior.class);
        addTestButton(TestInteractiveWindowGroup.class);
        addTestButton(TestWindowGroup.class);
       
        addText("Homework 2 Tests");
        addTestButton("TestTestFrame", TestTestFrame.class);
        addTestButton(TestOutlineRect.class);
        addTestButton(TestFilledRect.class);
        addTestButton(TestAllObjects.class);
        addTestButton(TestScaledGroup.class);
        addTestButton(TestLayoutGroup.class);
        addTestButton(TestSimpleGroup.class);
        addTestButton("RunAllExtendedTests", TestAllTests.class);
        addTestButton("BradsTestHomework2", TestHomework2.class);
                
    }
}