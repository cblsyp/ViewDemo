package com.example.chen.viewdemo.view;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.Rect;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

import com.example.chen.viewdemo.activity.SeekBarActivity;

public class CustView extends View{

	public  final int R1 = 250;
	public  final int R2 = 50;
	
	//ÿһ�� cos y�Ĳ��
	
	
	private  float width;
	private  float height;
  
	
	private   int newAngle=30;  //�µĽǶ� ��ʼʱ30
	private  int newAngle1;  //�µĽǶ� ��ʼʱ30
	
	public  double newX;
	public   double newY;   //��Main�ṩλ�� �ռ��ƶ������λ������ȥ
	
	private Paint mPaint2;
	private boolean flag=false;
	private Paint mPaint3;
	private Paint mPaint;
	private Context mContext;
	private double newAngle11;
    
	public CustView(Context context) {  
        this(context, null);  
    }  
	
	public CustView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		this.mContext=context;
		
		
	        
		initPaint();
	}

	/**
	 * ��ʼ������
	 */
	private void initPaint() {
		 // ʵ�������ʲ��򿪿����  
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);  
        mPaint.setStyle(Paint.Style.STROKE);  
        mPaint.setStrokeWidth(5);
        
        mPaint2 = new Paint(Paint.ANTI_ALIAS_FLAG);  
        mPaint2.setStyle(Paint.Style.FILL_AND_STROKE);  
        mPaint2.setColor(Color.BLUE);
        
       
        mPaint3 = new Paint(Paint.ANTI_ALIAS_FLAG);  
        
        mPaint3.setStrokeWidth(3);  
        mPaint3.setTextSize(80);  
        mPaint3.setColor(Color.CYAN);  
        mPaint3.setColor(Color.RED);  
        // ����������ʵ��ˮƽ���У�drawText��Ӧ��Ϊ����targetRect.centerX()  
        mPaint3.setTextAlign(Paint.Align.CENTER);  
        
        
        DisplayMetrics metric = new DisplayMetrics();
        
       ((SeekBarActivity) mContext).getWindowManager().getDefaultDisplay().getMetrics(metric);
        
        width = metric.widthPixels/2;
        height = metric.heightPixels/3;
        
        System.out.println(width+"---"+height);
        
	}

	@Override
	protected void onDraw(Canvas canvas) {
		
		// ����Բ��  
	    canvas.drawCircle(width, height, R1, mPaint); 
		
	    //��Բ��30�������360 ��λ�� ˳ʱ�뻭һ��Ĭ��С������
	    
	    System.out.println("���ڽǶ� "+newAngle);
	    
	    canvas.drawText(newAngle+"",width, height,mPaint3);  
       // canvas.drawText(newAngle+"", targetRect.centerX(), baseline, mPaint3);  
	    
	    
	    getNewLocation(); //�����ж� ���ƶ�С�� ����µ�λ��
	    
	    //ȷ��Main�пؼ���λ��
	   
	    System.out.println("newX " +newX +"---------- newY "+newY);
	    
	    if(newAngle==90){
	    	mPaint2.setColor(Color.BLACK);
	    }else if(newAngle==180){
	    	mPaint2.setColor(Color.RED);
	    }else if(newAngle==270){
	    	mPaint2.setColor(Color.DKGRAY);
	    }else if(newAngle==360){
	    	mPaint2.setColor(Color.MAGENTA);
	    }
	   canvas.drawCircle((float)newX, (float)newY, R2, mPaint2); 
	}

	public  void getNewLocation() {
		/**
	     * 0-90�ı仯����
	     */
		if(newAngle==0){
			newX=width;
			newY=height-R1;
		}
		else if(newAngle==90){
			newX=width+R1;
			newY=height;
		}
		else if(newAngle==180){
			newX=width;
			newY=height+R1;
		}
		else if(newAngle==270){
			newX=width-R1;
			newY=height;
		}
		else if(newAngle==360){
			newX=width;
			newY=height-R1;
		}
		else if(newAngle>360){
			newAngle=360;
			newX=width;
			newY=height-R1;
		}
		else if(newAngle>0&&newAngle<90){
	    newX = width+ (R1*Math.sin(newAngle*Math.PI/180));
	    newY = height-(R1*Math.cos(newAngle*Math.PI/180));
	    }
	    
	    /**
	     * 90-180�ı仯����
	     */
	    else if(newAngle>90&&newAngle<180){
	    	newAngle1=180-newAngle;
	    	 newX=width+ (R1*Math.sin(newAngle1*Math.PI/180));
	    	 newY=height+(R1*Math.cos(newAngle1*Math.PI/180));
	    }
	    
	    /**
	     * 180-270�ı仯����
	     */
	    else if(newAngle>180&&newAngle<270){
	    	newAngle1=270-newAngle;
	    	 newX=width- (R1*Math.cos(newAngle1*Math.PI/180));
	    	 newY=height+(R1*Math.sin(newAngle1*Math.PI/180));
	    }
	    
	    /**
	     * 270-360�ı仯����
	     */
	    else if(newAngle>270&&newAngle<360){
	    	newAngle1=360-newAngle;
	    	 newX=width- (R1*Math.sin(newAngle1*Math.PI/180));
	    	 newY=height-(R1*Math.cos(newAngle1*Math.PI/180));
	    }
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			flag=false;  //ÿ�ΰ��¶������¿�ʼ�ж�
			float action_x=event.getX();
			float action_y=event.getY();
			
			System.out.println("���λ�� �� x "+action_x+"   y "+action_y);
			
			//ֻ�е���������Ǹ�СԲ����ſ��Ա������ƶ�������
			// x>=newX-R2 && x<=newX+R2 y Ҳһ��
			System.out.println("x ��Χ: "+(newX-R2)+"-----"+(newX+R2));
			System.out.println("y ��Χ: "+(newY-R2)+"-----"+(newY+R2));
			
			if((action_x>=newX-R2 && action_x<=newX+R2)&&(action_y>=newY-R2&&action_y<=newY+R2)){
			flag=true;  //������С������
			}
			
			break;
		case MotionEvent.ACTION_MOVE:
		
			
			 if(flag){
				//�����ж� ��С��һ��һ���ƶ�
				//��Ϊ������� ��ߺ��ұ�   �ұ߾���   ��newAngle++
				float action_newY=event.getY();
				float action_newX=event.getX();
				
				if(newAngle==0){
					if(action_newY<newY){
						newAngle++;
					}
				}
				else if(newAngle==90){
					if(action_newY>newY){
						newAngle++;
					}else{
						newAngle--;
					}
				}
				else if(newAngle==180){
					if(action_newX>newX){   //180 �ұ�
						newAngle--;
					}else{
						newAngle++;
					}
				}
				else if(newAngle==270){
					if(action_newY<newY){
						newAngle++;
					}
					else{
						newAngle--;
					}
				}
				else if(newAngle==360){
					if(action_newX>newX){  //�Ȳ���������360
						//newAngle++;
					}else{
						newAngle--;
					}
				}

				else if(newAngle>0&&newAngle<90){
					double x=action_newX-width;
					double y=height-action_newY;
					System.out.println("x: "+x +"  y : "+y+"------------            "+(Math.atan(x/y)*180/Math.PI));
					if(y<0){
						newAngle=90;
					}
					else if(x<0){
						newAngle=0;
					}
					else{
					newAngle=(int) (Math.atan(x/y)*180/Math.PI);
					}
				}
				else if(newAngle>90&&newAngle<180){
					double x=action_newX-width;
					double y=action_newY-height;
					
					System.out.println("x: "+x +"  y : "+y+"------------"+(180-Math.atan(x/y)*180/Math.PI));
					
					if(y<0){
						newAngle=90;
					}
					else if(x<0){
						newAngle=180;
					}
					else
					newAngle=(int) (180-Math.atan(x/y)*180/Math.PI);
					
				}
				else if(newAngle>180&&newAngle<270){
					double x=width-action_newX;
					double y=action_newY-height;
					
					System.out.println("x: "+x +"  y : "+y+"------------"+(270-Math.atan(x/y)*180/Math.PI));
					
					if(x<0){
						newAngle=180;
					}
					else if(y<0){
						newAngle=270;
					}
					else
					newAngle=(int) (270- Math.atan(y/x)*180/Math.PI);
				}
				else if(newAngle>270&&newAngle<360){
					double x=width-action_newX;
					double y=height-action_newY;
					
					System.out.println("x: "+x +"  y : "+y+"------------"+(360-Math.atan(x/y)*180/Math.PI));
					
					if(y<0){
						newAngle=270;
					}
					else if(x<0){
						newAngle=360;
					}
					else
					newAngle=(int)(360- Math.atan(x/y)*180/Math.PI);
				}
				
				System.out.println(newAngle+"---�Ƕ�");
				
				invalidate();
			}
			break;
			
		case MotionEvent.ACTION_UP:
			
			break;

		default:
			break;
		}
		
		return true;
	}

}
