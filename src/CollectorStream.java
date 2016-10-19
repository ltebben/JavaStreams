import java.util.ArrayList;

public class CollectorStream extends Stream {
	private ArrayList<Producer> pVec = new ArrayList<Producer>();
	Notifier noti;
	TCFrame fr;
	
	public CollectorStream(Notifier not, TCFrame f){noti = not; fr = f;}
	
	public void add(Producer p){
		pVec.add(p);
	}
	
	public void run(){
		int total = 0;
		for(Producer p: pVec){
			total+=((Subscriber)p.next()).stock_value;
			fr.area.append("Collector: got " + p.ident +"\n");
		}
		noti.putValue(new IntObject(total));
		putIt(noti);
	}
	
}
