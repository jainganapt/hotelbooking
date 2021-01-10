package com.example.hotelbooking.utils;

import com.example.hotelbooking.model.RoomStatusInfo;

public class RoomResponseInfo {

	private ResponseInfo newResponseInfo;
	private RoomStatusInfo roomStatusInfo;

	public RoomStatusInfo getRoomStatusInfo() {
		return roomStatusInfo;
	}

	public void setRoomStatusInfo(RoomStatusInfo roomStatusInfo) {
		this.roomStatusInfo = roomStatusInfo;
	}

	public RoomResponseInfo() {

	}

	public ResponseInfo getNewResponseInfo() {
		return newResponseInfo;
	}

	public void setNewResponseInfo(ResponseInfo newResponseInfo) {
		this.newResponseInfo = newResponseInfo;
	}

}
