package com.example.recyclerviewpractice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>{

    private Context mctx;
    private List<Product> productlist;
    private OnItemClickListener mlistener;
    public ProductAdapter(Context mctx, List<Product> productlist)
    {
        this.mctx = mctx;
        this.productlist = productlist;
    }
    public interface OnItemClickListener
    {
        void onDeleteClick(int position);
        void onLinkClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener)
    {
        mlistener=listener;
    }
    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        LayoutInflater inflater=LayoutInflater.from(mctx);
        View view=inflater.inflate(R.layout.list_layout,null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position)
    {
        Product product=productlist.get(position);
        holder.title.setText(product.getTitle());
        holder.subtitle.setText(product.getSubtitle());
    }

    @Override
    public int getItemCount()
    {
        return productlist.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder
    {
        public TextView title,subtitle;
        public ImageView delete_button,link_button;
        public ProductViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.text_id);
            subtitle=itemView.findViewById(R.id.subtext_id);
            delete_button = itemView.findViewById(R.id.delete_button);
            link_button = itemView.findViewById(R.id.link_button);
            delete_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mlistener!=null)
                    {
                        int position=getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION)
                        {
                            mlistener.onDeleteClick(position);
                        }
                    }
                }
            });
            link_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mlistener!=null)
                    {
                        int position=getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION)
                        {
                            mlistener.onLinkClick(position);
                        }
                    }
                }
            });
        }

    }
}
