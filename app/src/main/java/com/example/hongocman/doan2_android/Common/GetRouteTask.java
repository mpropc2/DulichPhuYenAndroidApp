package com.example.hongocman.doan2_android.Common;

import java.util.ArrayList;
import org.w3c.dom.Document;

import com.example.hongocman.doan2_android.models.Helper;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.PolylineOptions;
import com.example.hongocman.doan2_android.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import layout.Fragment_bando;

/**
 * CLASS: 			GetRouteTask
 * @description: 	handle route direction response data: draw route direction line on map
 * @author: 		vandn
 * @created on:		09/08/2013
 * */
public class GetRouteTask extends AsyncTask<String, Void, String>{
	private Context mContext;
	private ProgressDialog pd;
	private LatLng fromPosition, toPosition;
    private String response = "";
    private Document document; 
    private String TDName;
	GoogleMap googleMap;
      
	public GetRouteTask(Context mContext, LatLng fromPostition, LatLng toPosition, String tdname, GoogleMap googleMap){
    	this.mContext = mContext;
    	this.fromPosition = fromPostition;
    	this.toPosition = toPosition;
    	this.TDName = tdname;
		this.googleMap = googleMap;
    }
    @Override
    protected void onPreExecute() {
        pd = Helper.showProgressBar(this.mContext, mContext.getResources().getString(R.string.msg_pd_search));
    }

    @Override
    protected String doInBackground(String... urls) {
          //Get All Route values
          document = RouteDirection.getDocument(this.fromPosition, this.toPosition, RouteDirection.MODE_WALKING);
          if(document!=null)
        	  response = "Success";
          return response;
    }

    @Override
    protected void onPostExecute(String result) {
    	try{
    	  pd.dismiss();
          googleMap.clear();
          if(response.equalsIgnoreCase("Success")){
	          ArrayList<LatLng> directionPoint = RouteDirection.getDirection(document);
	       
	          // Adding route on the map	     
	          googleMap.addPolyline((new PolylineOptions())
		                .addAll(directionPoint)
		                .width(3)
		                .color(Color.RED));
	          
	          String title; //snippet, address, sCoordinates, 
	          // adding marker route start point	
	          title = this.TDName ;//mContext.getString(R.string.title_TDlocation);
	          /*sCoordinates = MapCommon.getGPSCoordinates(this.fromPosition);
	          address = MapCommon.getAddressByLatlng(sCoordinates);
	  		  snippet = "RouteStart" +"@Địa chỉ: " + address;*/
	          
	  		  Marker marker = Helper.addMarkerOnMap(title,this.fromPosition, R.drawable.mylocation20, true, googleMap);
	  		  marker.showInfoWindow();
	  		  
	  		  // adding marker route end point
			  title = mContext.getString(R.string.title_cuslocation);
	          /*sCoordinates = MapCommon.getGPSCoordinates(this.toPosition);
	          address = MapCommon.getAddressByLatlng(sCoordinates);
	  		  snippet = "RouteEnd" +"@Địa chỉ: " + address;*/
	  		  
	          Helper.addMarkerOnMap(title, this.toPosition, R.drawable.flagposition, true, googleMap);

	          Helper.animeToLocation(googleMap, fromPosition);
	          Helper.setMapZoom(15, googleMap);
          }
          else{
        	  Helper.alertDialog("Kết nối đến trợ giúp chỉ đường của google thất bại. Vui lòng thử lại sau", mContext);
          }
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
    }
}


