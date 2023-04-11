package atomicspider.vxndo7z.projectmirrors.activity;

import android.os.*;
import atomicspider.vxndo7z.projectmirrors.game.*;
import vxndo.lib.support.app.*;

public class MainActivity
extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new Game(this));
		setSystemUiFlags(4098);
	}
}
