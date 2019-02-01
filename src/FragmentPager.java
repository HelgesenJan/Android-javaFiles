import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class FragmentPager extends FragmentStatePagerAdapter {

    int tabCount;

    public FragmentPager(FragmentManager fragmentManager, int tabCount){
        super(fragmentManager);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                FragmentGeneral fragmentGeneral = new FragmentGeneral();
                return fragmentGeneral;
            case 1:
                FragmentVideo fragmentVideo = new FragmentVideo();
                return fragmentVideo;
            default: return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
