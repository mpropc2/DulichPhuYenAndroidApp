package layout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hongocman.doan2_android.LazyloadFiles.ImageLoader;
import com.example.hongocman.doan2_android.R;
import com.example.hongocman.doan2_android.models.DanhMucApiModel;
import com.example.hongocman.doan2_android.models.Helper;
import com.example.hongocman.doan2_android.models.HinhAnhTable;
import com.example.hongocman.doan2_android.models.HinhAnhTask;

import org.w3c.dom.Text;

/**
 * Created by HoNgocMan on 11/26/2016.
 */

public class Fragment_chitiet extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public DanhMucApiModel danhMucApiModel;
    public int currentIndex = 0;

    private Fragment_chitiet.OnFragmentInteractionListener mListener;

    public Fragment_chitiet() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_chitiet.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_chitiet newInstance(DanhMucApiModel param1, String param2) {
        Fragment_chitiet fragment = new Fragment_chitiet();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam2 = getArguments().getString(ARG_PARAM2);
            danhMucApiModel = getArguments().getParcelable(ARG_PARAM1);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_danh_muc_chi_tiet, container, false);
        TextView tvTenDanhMuc = (TextView)view.findViewById(R.id.lbl_danh_muc);
        TextView tvSDT = (TextView)view.findViewById(R.id.lbl_sdt);
        TextView tvNoiDung = (TextView)view.findViewById(R.id.lbl_noidung);
        TextView tvDiaChi = (TextView)view.findViewById(R.id.lbl_diachi);

        ImageView image = (ImageView)view.findViewById(R.id.img_slider);

        Button btnPrev = (Button)view.findViewById(R.id.btn_previous);
        Button btnNext = (Button)view.findViewById(R.id.btn_next);
        Button btnCall = (Button)view.findViewById(R.id.btn_call);
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(danhMucApiModel.getSDT() != null && danhMucApiModel.getSDT() != "")
                    dialContactPhone(danhMucApiModel.getSDT());
            }
        });

        tvTenDanhMuc.setText(danhMucApiModel.getTenDanhMuc());
        tvSDT.setText(danhMucApiModel.getSDT());
        tvNoiDung.setText(danhMucApiModel.getMoTa());
        if(danhMucApiModel.getLstBanDo() != null && danhMucApiModel.getLstBanDo().size() != 0)
            tvDiaChi.setText(danhMucApiModel.getLstBanDo().get(0).getDiaChi());


        //set listhinh anh
        if(Helper.isNetworkConnected(getContext())){
            //lay hinh anh tu internet
            new HinhAnhTask(this, image).execute(danhMucApiModel.getMaDanhMuc());

        }else {
            //lay hinh anh tu sqlite
            //HinhAnhTable hinhAnhTable = new HinhAnhTable(getContext());
            //danhMucApiModel.setLstHinhAnh(hinhAnhTable.getByDanhMuc(danhMucApiModel.getMaDanhMuc()));
        }

        //tai hinh anh vao imageview
        ImageView imageSlider = (ImageView)view.findViewById(R.id.img_slider);


        //duyet hinh anh
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void dialContactPhone(final String phoneNumber) {
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null)));
    }
}

