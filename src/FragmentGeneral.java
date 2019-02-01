import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentGeneral extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_general, container, false);

        TextView link = view.findViewById(R.id.spruceLink);
        link.setMovementMethod(LinkMovementMethod.getInstance());

        TextView link2 = view.findViewById(R.id.feverLink);
        link2.setMovementMethod(LinkMovementMethod.getInstance());

        TextView link3 = view.findViewById(R.id.vomitLink);
        link3.setMovementMethod(LinkMovementMethod.getInstance());


        return view;
    }
}
