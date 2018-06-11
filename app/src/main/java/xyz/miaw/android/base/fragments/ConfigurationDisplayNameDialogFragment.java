package xyz.miaw.android.base.fragments;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.EditText;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConfigurationDisplayNameDialogFragment extends DialogFragment {

    public ConfigurationDisplayNameDialogFragment() {
        // Required empty public constructor
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        View view = getActivity().getLayoutInflater().inflate(xyz.miaw.android.base.R.layout.fragment_configuration_display_name_dialog, null);
        EditText displayName = (EditText) view.findViewById(xyz.miaw.android.base.R.id.edittext_configurationdisplayname_displayname);
        displayName.setText("Test");

        builder.setView(view);

        builder.setTitle(xyz.miaw.android.base.R.string.configurationdisplaynamedialog_title);
        builder.setPositiveButton(xyz.miaw.android.base.R.string.all_save, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dismiss();
            }
        });

        builder.setNegativeButton(xyz.miaw.android.base.R.string.all_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dismiss();
            }
        });

        return builder.create();
    }
}
