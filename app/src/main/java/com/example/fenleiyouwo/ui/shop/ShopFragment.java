package com.example.fenleiyouwo.ui.shop;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.fenleiyouwo.Data;
import com.example.fenleiyouwo.R;
import com.example.fenleiyouwo.ui.shop.ShopViewModel;

import java.util.ArrayList;
import java.util.List;


public class ShopFragment extends Fragment {
    private List<Item> itemList = new ArrayList<Item>();
    //private ShopViewModel shopViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_shop, container, false);
        initItems();
        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.recycler_view);
        StaggeredGridLayoutManager layoutManager = new
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        ItemAdapter adapter = new ItemAdapter(itemList);
        recyclerView.setAdapter(adapter);

        final Data app = (Data) getActivity().getApplication();
        String user_id=app.getid();


        return root;
    }

    private void initItems() {
        for (int i = 0; i < 1; i++) {
            Item bag = new Item("帆布包", R.drawable.bag,"积分：15",1);
            itemList.add(bag);
            Item badge = new Item("徽章", R.drawable.badge,"积分：10",2);
            itemList.add(badge);
            Item tshirt_white = new Item("文化衫白", R.drawable.tshirt_white,"积分：35",3);
            itemList.add(tshirt_white);
            Item tshirt_black = new Item("文化衫黑", R.drawable.tshirt_black,"积分：35",4);
            itemList.add(tshirt_black);
            Item logo = new Item("LOGO贴纸", R.drawable.logo,"积分：5",5);
            itemList.add(logo);
        }
    }
}
