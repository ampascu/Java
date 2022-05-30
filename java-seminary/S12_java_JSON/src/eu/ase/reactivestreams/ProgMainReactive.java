package eu.ase.reactivestreams;

import java.time.LocalDate;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.concurrent.Flow;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.SubmissionPublisher;

class NotificationJson {
	private JSONObject body;
	
	public NotificationJson(String message) throws JSONException {
		this.body = new JSONObject();
		body.put("message", message);
		body.put("date", LocalDate.now().toString());
	}
	
	public String getJsonMessage() throws JSONException {
		return this.body.getString("message");
	}
	
	public String getJsonDate() throws JSONException {
		return this.body.getString("date");
	}
}

class NotificationSubscriber implements Flow.Subscriber<NotificationJson>{

	private Subscription subscription;
	private static final int MAX_CALLS = 3;
	private static int notifReceived = 0;
	
	
	@Override
	public void onComplete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onError(Throwable arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNext(NotificationJson arg0) {
		try {
			System.out.println("Notif received! message: " + 
		arg0.getJsonMessage() + "; date: " + 
					arg0.getJsonDate());
			notifReceived++;
			
			if(notifReceived >= MAX_CALLS) {
				subscription.cancel();
				return;
			}
			
			subscription.request(1);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void onSubscribe(Subscription subscription) {
		// TODO Auto-generated method stub
		this.subscription = subscription;
		subscription.request(1);
	}
}

public class ProgMainReactive {

	
	public static void main(String[] args) {
		try( SubmissionPublisher<NotificationJson> publisher = new SubmissionPublisher<>()){
			NotificationSubscriber notfS = new NotificationSubscriber();
			
			publisher.subscribe(notfS);
			
			try {
				List.of(
						new NotificationJson("Msg 1"),
						new NotificationJson("Msg 2"), 
						new NotificationJson("Msg 3")).forEach(publisher::submit);
				
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			while(publisher.hasSubscribers()) {
				
			}
			
			System.out.println("No more subscribers");
		}

	}

}
