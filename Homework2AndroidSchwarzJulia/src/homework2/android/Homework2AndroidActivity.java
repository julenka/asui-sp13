package homework2.android;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Homework2AndroidActivity extends Activity {
    /** Called when the activity is first created. */
	
	private Button bTestTestFrame;
	private Button bTestOutlineRect;
	private Button bTestFilledRect;
	private Button bTestAll;
	private Button bTestSimpleGroup;
	private Button bTestLayoutGroup;
	private Button bTestScaledGroup;
	private Button bTestAllTests;
	private Button bTestHomework2;
	
	private Context context;   
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        context = this;
        
        bTestTestFrame = (Button) findViewById(R.id.testTestFrame);
        bTestTestFrame.setText("TestTestFrame");
        bTestTestFrame.setOnClickListener(new OnClickListener(){
        	public void onClick(View v){        		
        		 Intent i = new Intent(context, TestTestFrame.class);                    
        	     startActivity(i);
        	}
        });
        
        bTestOutlineRect = (Button) findViewById(R.id.testRect);
        bTestOutlineRect.setEnabled(true);
        bTestOutlineRect.setText("TestOutlineRect");
        bTestOutlineRect.setOnClickListener(new OnClickListener(){
        	public void onClick(View v){
        		Intent i = new Intent(context, TestOutlineRect.class);      
        		i.putExtra("lineThickness", "");
        		startActivity(i);
        	}
        });
        
        bTestFilledRect = (Button) findViewById(R.id.testFillRect);
        bTestFilledRect.setEnabled(true);
        bTestFilledRect.setText("TestFillRect");
        bTestFilledRect.setOnClickListener(new OnClickListener(){
        	public void onClick(View v){
        		Intent i = new Intent(context, TestFilledRect.class);      
        		i.putExtra("lineThickness", "");
        		startActivity(i);
        	}
        });
        	
        
        bTestAll = (Button) findViewById(R.id.testAll);
        bTestAll.setEnabled(true);
        bTestAll.setText("TestAllObjects");
        bTestAll.setOnClickListener(new OnClickListener(){
        	public void onClick(View v){
        		Intent i = new Intent(context, TestAllObjects.class);      
        		startActivity(i);  
        	}
        });
        
        bTestLayoutGroup = (Button) findViewById(R.id.testLayoutGroup);
        bTestLayoutGroup.setEnabled(true);
        bTestLayoutGroup.setText("TestLayoutGroup");
        bTestLayoutGroup.setOnClickListener(new OnClickListener(){
        	public void onClick(View v){
        		Intent i = new Intent(context, TestLayoutGroup.class);      
        		i.putExtra("nObject", "");
        		startActivity(i);        		
        	}
        });
        
        bTestScaledGroup = (Button) findViewById(R.id.testLayoutGroup);
        bTestScaledGroup.setEnabled(true);
        bTestScaledGroup.setText("TestScaledGroup");
        bTestScaledGroup.setOnClickListener(new OnClickListener(){
        	public void onClick(View v){
        		Intent i = new Intent(context, TestScaledGroup.class);      
        		i.putExtra("nObject", "");
        		startActivity(i);        		
        	}
        });
        
        bTestSimpleGroup = (Button) findViewById(R.id.testSimpleGroup);
        bTestSimpleGroup.setEnabled(true);
        bTestSimpleGroup.setText("TestSimpleGroup");
        bTestSimpleGroup.setOnClickListener(new OnClickListener(){
        	public void onClick(View v){
        		Intent i = new Intent(context, TestSimpleGroup.class);      
        		i.putExtra("nObject", "");
        		startActivity(i); 
        	}
        });
        
        
        bTestAllTests = (Button) findViewById(R.id.runAllTests);
        bTestAllTests.setEnabled(true);
        bTestAllTests.setText("RunAllExtendedTests");
        bTestAllTests.setOnClickListener(new OnClickListener(){
        	public void onClick(View v){
        		Intent i = new Intent(context, TestAllTests.class);      
        		i.putExtra("nObject", "");
        		startActivity(i); 
        	}
        });
        
        bTestHomework2 = (Button) findViewById(R.id.runAllTests);
        bTestHomework2.setEnabled(true);
        bTestHomework2.setText("TestHomework2");
        bTestHomework2.setOnClickListener(new OnClickListener(){
        	public void onClick(View v){
        		Intent i = new Intent(context, TestHomework2.class);      
        		i.putExtra("nObject", "");
        		startActivity(i); 
        	}
        });
                
    }
}