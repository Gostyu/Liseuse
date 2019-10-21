package upec.projetandroid20182019;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class TextsAdapter extends RecyclerView.Adapter<TextsAdapter.TextsViewHolder> {
    List<String> texts;
    Context context;
    public TextsAdapter(Context context,List<String> texts) {
        this.texts = texts;
        this.context=context;
    }
    public class TextsViewHolder extends RecyclerView.ViewHolder {
        TextView tv;
        public TextsViewHolder(@NonNull View itemView) {
            super(itemView);
            tv=itemView.findViewById(R.id.textView);
        }
    }
    @NonNull
    @Override
    public TextsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View item= LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.list_item,viewGroup,false);
        TextsViewHolder tvh=new TextsViewHolder(item);
        return tvh;
    }

    @Override
    public void onBindViewHolder(@NonNull TextsViewHolder textsViewHolder, int i) {
        final String fileName=texts.get(i);
        textsViewHolder.tv.setText(fileName);
        textsViewHolder.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,fileName, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(context,Liseuse.class);
                intent.putExtra("textFile",fileName);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return texts.size();
    }

}
