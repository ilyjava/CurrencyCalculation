package main;

import java.io.IOException;

import com.google.gson.Gson;

import graphics.MyFrame;
import model.CurrencyModelCurrent;
import model.CurrencyModelPast;
import model.Results;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Main {
	
	private static OkHttpClient client = new OkHttpClient();
	
	public static void main(String[] args) {
	MyFrame frame = new MyFrame();
	Results res = new Results();
	}
		
	public static String getJSON(String url) throws IOException{
		
		Request request = new Request.Builder()
				.url(url)
				.build();
		Response response = client.newCall(request).execute();
		return response.body().string();
	}
	
	public static String[] getPastData(String date) {
		String json = null;
		try {
			json = getJSON("https://exchangeratesapi.io/api/" + date + "?access_key=7fc3201c182da64b6340191f73a5cae4&base=USD&symbols=RUB");
	} catch (Exception e) {
		e.printStackTrace();
	}
	
		Gson gson = new Gson();
		
		CurrencyModelPast currency = gson.fromJson(json, CurrencyModelPast.class);
		return new String[] {
		   currency.getBase(),
		   currency.getDate(),
		   currency.getRates().getRUB()
		};
   }

	public static String[] getCurrentData() {
		String json = null;
		try {
			json = getJSON("https://exchangeratesapi.io/api/latest?access_key=7fc3201c182da64b6340191f73a5cae4&base=USD&symbols=RUB");
	} catch (Exception e) {
		e.printStackTrace();
	}
	
		Gson gson = new Gson();
		
		CurrencyModelCurrent currency = gson.fromJson(json, CurrencyModelCurrent.class);
		
		return new String[] {
		   currency.getBase(),
		   currency.getDate(),
		   currency.getRates().getRUB()
		};
   }
	
	public static String calculationOfProfit(String amount, String pastRate, String currentRate) {
		int currencyAmount = Integer.parseInt(amount);
		double currencyPastRate = Double.parseDouble(pastRate);
		double currencyCurrentRate = Double.parseDouble(currentRate);
		int profit = (int) (currencyPastRate-(currencyCurrentRate+(currencyCurrentRate/(100/0.5))))*currencyAmount; //прошлый курс валюты - настоящий курс валюты с учетом spread
		String result = Integer.toString(profit);
		return result;
	}
	
}
