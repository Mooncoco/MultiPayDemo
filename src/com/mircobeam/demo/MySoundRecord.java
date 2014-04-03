package com.mircobeam.demo;

import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MySoundRecord extends Activity
{
	private static int MAX_TIME = 0;    		//最长录制时间，单位秒，0为无时间限制
	private static int MIX_TIME = 1;     		//最短录制时间，单位秒，0为无时间限制，建议设为1
	
	private static float recodeTime=0.0f;    //录音的时间
	
	Button buttonStartRecord;
	Button buttonStopRecord;
	Button buttonPlayRecord;
	Button buttonSendRecord;
	Button buttonDeleteRecord;
	
	private MediaRecorder mediarecorder = null;
	private MediaPlayer mediaplayer = null;
	
	private File tempMeidFile;
	private File mediapath;
	
	private boolean isSDcard;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.sound_record);
		
		buttonStartRecord = (Button) findViewById(R.id.button_start);
		buttonStartRecord.setOnClickListener(onClickListener);
		
		buttonStopRecord = (Button) findViewById(R.id.button_stop);
		buttonStopRecord.setOnClickListener(onClickListener);
		
		buttonPlayRecord = (Button) findViewById(R.id.button_play);
		buttonPlayRecord.setOnClickListener(onClickListener);
		
		buttonSendRecord = (Button) findViewById(R.id.button_send);
		buttonSendRecord.setOnClickListener(onClickListener);
		
		buttonDeleteRecord = (Button) findViewById(R.id.button_delete);
		buttonDeleteRecord.setOnClickListener(onClickListener);
		
		isSDcard = Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
		
		if(isSDcard) 
		{
			mediapath = Environment.getExternalStorageDirectory();
			System.out.println("文件路径:" + mediapath);
		}
		else
		{
			//如果SDcard不存在，提示并退出程序
			Toast.makeText(MySoundRecord.this, "SDCard不存在，请插入SDcard", Toast.LENGTH_SHORT).show();;
			this.finish();
		}
	}
	
	private OnClickListener onClickListener = new OnClickListener()
	{
		
		@Override
		public void onClick(View v)
		{
			if(v == buttonStartRecord)
			{
				startSoundRecord();
			}
			else if(v == buttonStopRecord)
			{
				stopSoundRecord();
			}
			else if(v == buttonPlayRecord)
			{
				playSoundRecord();
			}
			else if(v == buttonSendRecord)
			{
				sendSoundRecord();
			}
			else if(v == buttonDeleteRecord)
			{
				
			}
		}
	};
	
	private void startSoundRecord()
	{
		try
		{
			tempMeidFile = File.createTempFile("tempMediaFile", ".arm", mediapath);
			//生成一个MediaRecorder对象
			mediarecorder = new MediaRecorder();
			//设置音频源为麦克风
			mediarecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
			//设置录制的音频格式
			mediarecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
			//设置录制音频的编码
			mediarecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
			//设置储存的文件路径
			mediarecorder.setOutputFile(tempMeidFile.getAbsolutePath());
			//准备录制
			mediarecorder.prepare();
			//开始录制
			mediarecorder.start();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		buttonStartRecord.setEnabled(false);
		buttonStartRecord.setText("正在录音...");
	}
	
	private void stopSoundRecord()
	{
		if (mediarecorder != null)
		{
			// 停止录音
			mediarecorder.stop();
			// 释放资源
			mediarecorder.release();
			mediarecorder = null;
			// 设置按键的变化
			buttonStartRecord.setText("开始录音");
			buttonStartRecord.setEnabled(true);
			buttonPlayRecord.setEnabled(true);
			buttonStopRecord.setEnabled(true);
			
			System.out.println("stop sound record");
		}
		else
		{
			System.out.println("this is null");
		}
	}
	
	private void playSoundRecord()
	{
		mediaplayer = new MediaPlayer();
		//重置播放器
		mediaplayer.reset();
		//得到音频文件
		try
		{
			mediaplayer.setDataSource(tempMeidFile.getAbsolutePath());
			//准备播放
			mediaplayer.prepare();
			//开始播放
			mediaplayer.start();
			buttonStartRecord.setEnabled(false);
			//设置当播放完了的监听
			mediaplayer.setOnCompletionListener(new OnCompletionListener() 
			{
				@Override
				public void onCompletion(MediaPlayer mp)
				{
					if (tempMeidFile.exists())
					{
						buttonStartRecord.setEnabled(true);
						
						if (tempMeidFile.isFile())
						{
							Toast.makeText(MySoundRecord.this, "文件播放完成", Toast.LENGTH_SHORT).show();
						}
						else
						{
							Toast.makeText(MySoundRecord.this, "不是文件", Toast.LENGTH_SHORT).show();
						}
					}
					else
					{
						Toast.makeText(MySoundRecord.this, "文件不存在", Toast.LENGTH_SHORT).show();
					}
				}

			});
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private void sendSoundRecord()
	{
		buttonSendRecord.setText("正在发送..");
	}
	
	private void deleteSoundRecord()
	{
		tempMeidFile.delete();
	}

}
