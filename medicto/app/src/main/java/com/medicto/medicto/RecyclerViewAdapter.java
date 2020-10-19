package com.medicto.medicto;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.medicto.medicto.Fragment.Main_Fragment_Your_Treatment;

import java.util.ArrayList;
import java.util.BitSet;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<RecyclerItem> mList = null;
    private ArrayList<String> mList2 = null;

    String Language=null;



    private OnItemClickListener mListener = null;

    private OnItemRemoveListener onItemRemoveListener = null;
    private Context mContext;

    public interface OnItemClickListener{
        void onItemClick(View v, int position);
    }

    public interface OnItemRemoveListener{
        void onItemRemove(View v, int position);
    }

    public void setOnItemRemoveListener(OnItemRemoveListener onItemRemoveListener){
        this.onItemRemoveListener = onItemRemoveListener;

    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.mListener = onItemClickListener;
    }

    public RecyclerViewAdapter(ArrayList<RecyclerItem> mList){
        this.mList = mList;
    }






    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        Context context = parent.getContext();

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (viewType == RecyclerViewType.ViewType.ButtonView){
            view = inflater.inflate(R.layout.recycler_button_text_item ,parent,false);
            return new ButtonText(view);
        }else if(viewType == RecyclerViewType.ViewType.CircleImageTextView){
            view = inflater.inflate(R.layout.recycler_circle_image_item ,parent,false);
            return new CircleImageTextView(view);
        }else if(viewType == RecyclerViewType.ViewType.SquareImageTextView){
            view = inflater.inflate(R.layout.recycler_square_image_item ,parent,false);
            return new SquareImageTextView(view);
        }else if (viewType == RecyclerViewType.ViewType.ImageButtonGreen){

            view = inflater.inflate(R.layout.recycler_button_text_item_default_green,parent,false);
            return new ImageButton_ColorGreen(view);
        }else if (viewType == RecyclerViewType.ViewType.InstructionItem){
            view = inflater.inflate(R.layout.recycler_instruction_item,parent,false);
            return new InstructionItem(view);

        }else{
            view = inflater.inflate(R.layout.recycler_history_item,parent,false);
            return new HistoryItem(view);
        }



    }

    public void setContext(Context context){
        this.mContext = context;
    }

    public void setLanguage(String lang){
        this.Language = lang;
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ButtonText) {

            ((ButtonText)holder).button.setText(mList.get(position).getTitle());

        }else if(holder instanceof CircleImageTextView){

            ((CircleImageTextView)holder).textView.setText(mList.get(position).getTitle());
            ((CircleImageTextView)holder).imageButton.setImageDrawable(mList.get(position).getDrawable());


        }else if (holder instanceof SquareImageTextView){
            ((SquareImageTextView)holder).textView.setText(mList.get(position).getTitle());
            ((SquareImageTextView)holder).imageButton.setImageDrawable(mList.get(position).getDrawable());

        }else if( holder instanceof ImageButton_ColorGreen){
            ((ImageButton_ColorGreen)holder).button.setText(mList.get(position).getTitle());
            //((ImageButton_ColorGreen)holder).imageButton.setImageDrawable(mList.get(position).getDrawable());
        }else if(holder instanceof  InstructionItem){
            ((InstructionItem)holder).textviewMedicineName.setText(mList.get(position).getMedicineName());
            ((InstructionItem)holder).textViewday.setText(mList.get(position).getDay());
            ((InstructionItem)holder).textViewduration.setText(mList.get(position).getDuration());
            ((InstructionItem)holder).textViewtiming.setText(mList.get(position).getTiming2());
            ((InstructionItem)holder).textViewtiming2.setText(mList.get(position).getTiming());
            ((InstructionItem)holder).textviewTime.setText(mList.get(position).getTime());

            switch (Language){
                case "한국어":

                    ((InstructionItem)holder).textviewday2.setText("복약량");
                    ((InstructionItem)holder).textviewwhen.setText("언제");
                    ((InstructionItem)holder).textviewduration2.setText("기간");
                    ((InstructionItem)holder).textviewSideEffect.setText("주의사항");
                    break;
                case"ENGLISH":
                    ((InstructionItem)holder).textviewday2.setText("Dosage");
                    ((InstructionItem)holder).textviewwhen.setText("When");
                    ((InstructionItem)holder).textviewduration2.setText("Period");
                    ((InstructionItem)holder).textviewSideEffect.setText("Caution");
                    break;
                case"French":
                    ((InstructionItem)holder).textviewday2.setText("Dose");
                    ((InstructionItem)holder).textviewwhen.setText("Quand");
                    ((InstructionItem)holder).textviewduration2.setText("Période");
                    ((InstructionItem)holder).textviewSideEffect.setText("Attention");
                    break;
                case "汉语":
                    ((InstructionItem)holder).textviewday2.setText("用药量");
                    ((InstructionItem)holder).textviewwhen.setText("什么时候");
                    ((InstructionItem)holder).textviewduration2.setText("期间");
                    ((InstructionItem)holder).textviewSideEffect.setText("注意事项");
                    break;
            }



            ArrayList<String> arrayList = mList.get(position).getList();
            ArrayList<String> arrayList2 = mList.get(position).getEffectList();

            RecyclerViewAdapter_Segment recyclerViewAdapter_segment = new RecyclerViewAdapter_Segment(arrayList);
            RecyclerViewAdapter_Segment2 recyclerViewAdapter_segment1 = new RecyclerViewAdapter_Segment2(arrayList2);

           // RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(mList,mList2);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false);
            LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false);
            ((InstructionItem)holder).recyclerView.setLayoutManager(linearLayoutManager);
            ((InstructionItem)holder).recyclerView.setAdapter(recyclerViewAdapter_segment);

            ((InstructionItem)holder).recyclerView2.setLayoutManager(linearLayoutManager1);
            ((InstructionItem)holder).recyclerView2.setAdapter(recyclerViewAdapter_segment1);


            recyclerViewAdapter_segment1.notifyDataSetChanged();
            recyclerViewAdapter_segment.notifyDataSetChanged();


        }else if(holder instanceof HistoryItem){
            ((HistoryItem)holder).medicineName.setText(mList.get(position).getMedicineName());
            ((HistoryItem)holder).medicineCount.setText(mList.get(position).getMedicineCount());
            ((HistoryItem)holder).medicineDuration.setText(mList.get(position).getMedicineDuration());
            ((HistoryItem)holder).time.setText(mList.get(position).getTime());
            ((HistoryItem)holder).period.setText(mList.get(position).getPeriod());
        }

    }



    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mList.get(position).getViewType();
    }

    public class HistoryItem extends RecyclerView.ViewHolder{
        TextView medicineName,medicineCount,medicineDuration,period,time;
        LinearLayout linearLayout;


        HistoryItem(View view){
            super(view);

            medicineName = (TextView)view.findViewById(R.id.recycler_history_textview_medicine_name);
            medicineCount = (TextView)view.findViewById(R.id.recycler_history_textview_medicine_count);
            medicineDuration = (TextView)view.findViewById(R.id.recycler_history_textview_medicine_duration);
            period = (TextView)view.findViewById(R.id.recycler_history_textview_period);
            time = (TextView)view.findViewById(R.id.recycler_history_textview_time);
            linearLayout = (LinearLayout)view.findViewById(R.id.recycler_history_linearLayout);

            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION){
                        if (mListener != null){
                            mListener.onItemClick(v,pos);

                        }
                    }
                }
            });

        }
    }

    public class InstructionItem extends RecyclerView.ViewHolder{

        public TextView textViewday,textViewduration,textViewtiming,textViewtiming2;
        public TextView textviewday2,textviewwhen,textviewduration2,textviewMedicineName,textviewSideEffect,textviewTime;
        RecyclerView recyclerView,recyclerView2;
        public LinearLayout linearLayout;

        InstructionItem(View view){
            super(view);

            linearLayout = (LinearLayout)view.findViewById(R.id.recycleR_instruction_linearLayout);
            textViewday = (TextView)view.findViewById(R.id.recycler_instruction_item_textview_day);
            textViewduration = (TextView)view.findViewById(R.id.recycler_instruction_item_textview_duration);
            textViewtiming = (TextView)view.findViewById(R.id.recycler_instruction_item_textview_timing);
            textViewtiming2 = (TextView)view.findViewById(R.id.recycler_instruction_item_textview_timing2);
            recyclerView = (RecyclerView)view.findViewById(R.id.recycler_instruction_item_recycler);
            recyclerView2 = (RecyclerView)view.findViewById(R.id.recycler_instruction_item_recycler2);
            textviewday2 = (TextView)view.findViewById(R.id.recycler_instruction_item_textview_day2);
            textviewwhen = (TextView)view.findViewById(R.id.recycler_instruction_item_textview_when);
            textviewduration2 = (TextView)view.findViewById(R.id.recycler_instruction_item_textview_duration2);
            textviewMedicineName = ( TextView)view.findViewById(R.id.recycler_instruction_item_textview_medicine_name);
            textviewSideEffect = (TextView)view.findViewById(R.id.recycler_instruction_item_textview_sideeffect);
            textviewTime = (TextView)view.findViewById(R.id.recycler_instruction_item_textview_time);

            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION){
                        if (mListener != null){
                            mListener.onItemClick(v,pos);

                        }
                    }
                }
            });
        }
    }

    public class CircleImageTextView extends RecyclerView.ViewHolder{
        LinearLayout linearLayout;
        ImageButton imageButton;
        TextView textView;

        CircleImageTextView(View view){
            super(view);
            linearLayout = (LinearLayout)view.findViewById(R.id.recycler_circle_item_linearlayout);
            imageButton = (ImageButton)view.findViewById(R.id.recycler_circle_item_imagebutton);
            textView = (TextView)view.findViewById(R.id.recycler_circle_item_textview);

            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION){
                        if (mListener != null){
                            mListener.onItemClick(v,pos);
                            if (imageButton.isSelected()){
                                System.out.println("으아아아체크체크");
                                imageButton.setSelected(false);
                                linearLayout.setBackgroundResource(R.drawable.circle_background_white);
                            }else {
                                System.out.println("으아아아 안체크 안체크");
                                imageButton.setSelected(true);
                                linearLayout.setBackgroundResource(R.drawable.circle_background_green);
                            }
                        }
                    }
                }
            });

        }
    }

    public class SquareImageTextView extends RecyclerView.ViewHolder{
        LinearLayout linearLayout;
        ImageButton imageButton;
        TextView textView;

        SquareImageTextView(View view){
            super(view);
            linearLayout = (LinearLayout)view.findViewById(R.id.recycler_square_item_linearlayout);
            imageButton = (ImageButton)view.findViewById(R.id.recycler_square_item_imagebutton);
            textView = (TextView)view.findViewById(R.id.recycler_square_item_textview);

            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION){
                        if (mListener != null){
                            mListener.onItemClick(v,pos);
                            if (imageButton.isSelected()){
                                System.out.println("으아아아체크체크");
                                imageButton.setSelected(false);
                                linearLayout.setBackgroundResource(R.drawable.square_background_white);
                            }else {
                                System.out.println("으아아아 안체크 안체크");
                                imageButton.setSelected(true);
                                linearLayout.setBackgroundResource(R.drawable.square_background_green);
                            }
                        }
                    }
                }
            });
        }
    }

    public class ButtonText extends RecyclerView.ViewHolder{
        Button button;
        Button button2;

        ButtonText(View view){
            super(view);
            button = (Button)view.findViewById(R.id.recycler_button_text_item_button);
            button2 = (Button)view.findViewById(R.id.recycler_button_text_item_button2);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION){
                        if (mListener != null){
                            mListener.onItemClick(v,pos);
                        }
                    }
                }
            });
            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION){
                        if (onItemRemoveListener != null){
                            onItemRemoveListener.onItemRemove(v,pos);
                        }
                    }
                }
            });
        }
    }

    public class ImageButton_ColorGreen extends RecyclerView.ViewHolder{
        Button button;
        ImageButton imageButton;

        ImageButton_ColorGreen(View view){
            super(view);
            button = (Button)view.findViewById(R.id.recycler_button_text_item_default_green_button);
            imageButton = (ImageButton) view.findViewById(R.id.recycler_button_text_item_default_green_image_button_plus);

            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION){
                        if (mListener != null){
                            mListener.onItemClick(v,pos);
                        }
                    }
                }
            });
        }
    }



}
