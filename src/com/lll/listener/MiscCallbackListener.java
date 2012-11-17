package com.lll.listener;

public interface MiscCallbackListener {
	interface OnLoginProcessListener  {
		
	}
	interface OnLoginBackGroundProcessListener  {
		public void finishLoginProcess(int code); 
	}
}
