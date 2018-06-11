package xyz.miaw.android.base.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import xyz.miaw.android.base.models.SharedAccount;

// Reference: https://android.jlelse.eu/click-listener-for-recyclerview-adapter-2d17a6f6f6c9
public class AccountsRecyclerViewAdapter extends RecyclerView.Adapter {

    private List<SharedAccount> _sharedAccounts;
    private RecyclerView _recyclerView;

    // onClickListener
    private AccountsRecyclerViewOnClickListener _accountsRecyclerViewOnClickListener;

    public  AccountsRecyclerViewAdapter(List<SharedAccount> sharedAccounts, AccountsRecyclerViewOnClickListener accountsRecyclerViewOnClickListener){

        _sharedAccounts = sharedAccounts;
        _accountsRecyclerViewOnClickListener = accountsRecyclerViewOnClickListener;

    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

        AccountsRecyclerViewViewHolder accountsRecyclerViewViewHolder = (AccountsRecyclerViewViewHolder) viewHolder;

        SharedAccount currentSharedAccount = _sharedAccounts.get(position);

        // Change subtitle based on status
        accountsRecyclerViewViewHolder.title.setText("Akaun #" + currentSharedAccount.AccountNumber);
        accountsRecyclerViewViewHolder.detailOne.setText("RM " + String.format("%1$,.2f", currentSharedAccount.BakiOrNilai));
        accountsRecyclerViewViewHolder.accountNumber = currentSharedAccount.AccountNumber;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(xyz.miaw.android.base.R.layout.item_account, parent, false);

        // No listener
//        AccountsRecyclerViewViewHolder accountsRecyclerViewViewHolder = new AccountsRecyclerViewViewHolder(itemView);

        // With listener
        AccountsRecyclerViewViewHolder accountsRecyclerViewViewHolder = new AccountsRecyclerViewViewHolder(itemView, _accountsRecyclerViewOnClickListener);

        return accountsRecyclerViewViewHolder;

    }

    @Override
    public int getItemCount() {
        return _sharedAccounts.size();
    }

    public void addToList(List<SharedAccount> accounts)    {
        int positionStart = _sharedAccounts.size();
        int newItemsCount = _sharedAccounts.size();

        if (newItemsCount == 0)
        {
            return;
        }

        for (SharedAccount sharedAccount : _sharedAccounts){
            _sharedAccounts.add(sharedAccount);
        }

        notifyItemRangeInserted(positionStart, newItemsCount);
        // Need to disable animation because the animation resets everything to hidden first
        // _recyclerView.ScheduleLayoutAnimation();
    }

    public void removeAllFromList(){

        _sharedAccounts.clear();
        notifyDataSetChanged();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        _recyclerView = recyclerView;
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class AccountsRecyclerViewViewHolder
            extends RecyclerView.ViewHolder
            implements View.OnClickListener{

        // each data item is just a string in this case
        public TextView title;
        public TextView detailOne;
        public String accountNumber;

        // listener
        private AccountsRecyclerViewOnClickListener _listener;

//        // Constructor
//        public AccountsRecyclerViewViewHolder(View view) {
//
//            super(view);
//
//            title = view.findViewById(R.id.textview_item_accountnumber);
//            detailOne = view.findViewById(R.id.textview_item_amount);
//        }

        // Constructor with listener
        public AccountsRecyclerViewViewHolder(View view, AccountsRecyclerViewOnClickListener onClickListener) {

            super(view);

            title = view.findViewById(xyz.miaw.android.base.R.id.textview_item_accountnumber);
            detailOne = view.findViewById(xyz.miaw.android.base.R.id.textview_item_amount);

            // set listener
            _listener = onClickListener;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            _listener.onClick(view, accountNumber);
        }

    }

    public interface AccountsRecyclerViewOnClickListener {

        void onClick(View view, String accountNumber);

    }
}
