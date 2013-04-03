package homework4.android.app;


import homework4.android.R;
import homework4.android.test.MyConstraintsTest;
import homework4.android.test.MyTestAllTests;
import homework4.android.test.MyTestFilledRect;
import homework4.android.test.MyTestInteractiveWindowGroup;
import homework4.android.test.MyTestLayoutGroup;
import homework4.android.test.MyTestNewRectBehavior;
import homework4.android.test.MyTestNotifyPropertyChanged;
import homework4.android.test.MyTestScaledGroup;
import homework4.android.test.MyTestTestFrame;
import homework4.android.test.MyTestWindowGroup;
import homework4.android.test.TestAllObjects;
import homework4.android.test.TestHomework2;
import homework4.android.test.TestHomework3;
import homework4.android.test.TestHomework4;
import homework4.android.test.TestOutlineRect;
import homework4.android.test.TestSimpleGroup;
import homework4.android.test.ValueRectTest;
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
        addTestButton(MyConstraintsTest.class);
        addTestButton(ValueRectTest.class);
        addTestButton(NodeEdgeEditor.class);
        addTestButton(TestHomework4.class);
        addTestButton(MyTestNotifyPropertyChanged.class);
        
        addText("Homework 3 Tests");
        addTestButton(TestHomework3.class);
        addTestButton(DrawingEditor.class);
        addTestButton(MyTestNewRectBehavior.class);
        addTestButton(MyTestInteractiveWindowGroup.class);
        addTestButton(MyTestWindowGroup.class);
       
        addText("Homework 2 Tests");
        addTestButton("TestTestFrame", MyTestTestFrame.class);
        addTestButton(TestOutlineRect.class);
        addTestButton(MyTestFilledRect.class);
        addTestButton(TestAllObjects.class);
        addTestButton(MyTestScaledGroup.class);
        addTestButton(MyTestLayoutGroup.class);
        addTestButton(TestSimpleGroup.class);
        addTestButton("RunAllExtendedTests", MyTestAllTests.class);
        addTestButton("BradsTestHomework2", TestHomework2.class);
                
    }
}