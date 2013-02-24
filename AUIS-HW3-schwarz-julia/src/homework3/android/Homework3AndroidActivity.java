package homework3.android;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Homework3AndroidActivity extends Activity {
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