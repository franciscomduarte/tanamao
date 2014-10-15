package br.com.tnm.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import br.com.tnm.R;
import br.com.tnm.bean.Feed;

public class FeedListAdapter extends ArrayAdapter<Feed> {
	List<Feed> imageETexto =null;

	public FeedListAdapter(Activity activity, List<Feed> imageETexto) {
		super(activity, 0, imageETexto);
		this.imageETexto = imageETexto;
	}


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
        
        View v = convertView;
		
		    if (v==null){
				Activity activity = (Activity) getContext();
				LayoutInflater inflater = activity.getLayoutInflater();
		        v = inflater.inflate(R.layout.rssfeedadapter_layout, null);
		     }
		
        	Feed o = imageETexto.get(position);
		//	ImageView imageView = (ImageView) v.findViewById(R.id.feed_image);

	        if(o!=null){
	        TextView textView = (TextView) v.findViewById(R.id.feed_text);
			TextView timeFeedText = (TextView) v.findViewById(R.id.feed_updatetime);
			
			if(textView!=null){
			textView.setText(imageETexto.get(position).getTitulo());
			SpannableString content = new SpannableString(imageETexto.get(position).getDataPublicacao());
			content.setSpan(new UnderlineSpan(), 0, 13, 0);
			timeFeedText.setText(content);
			}
	       }
		
	      try {
			if(imageETexto.get(position).getImgLink() !=null){


				URL feedImage= new URL(imageETexto.get(position).getImgLink().toString());
				if(!feedImage.toString().equalsIgnoreCase("null")){
					HttpURLConnection conn= (HttpURLConnection)feedImage.openConnection();
					InputStream is = conn.getInputStream();
					Bitmap img = BitmapFactory.decodeStream(is);
					//imageView.setImageBitmap(img);
				}
				else{
					//imageView.setBackgroundResource(R.drawable.logo);
				}
			}


		} catch (MalformedURLException e) {

		}
		catch (IOException e) {

		}

		return v;

	}


	
}