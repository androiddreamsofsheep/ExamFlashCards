package edu.dlsu.mobapde.examflashcards;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by courtneyngo on 06/02/2017.
 */
public class Term implements Parcelable {

    private String term;
    private String definition;

    private int layoutPosition; // to be set only in onBindViewHolder

    public Term(){}

    public Term(String term, String definition) {
        this.term = term;
        this.definition = definition;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public int getLayoutPosition() {
        return layoutPosition;
    }

    public void setLayoutPosition(int layoutPosition) {
        this.layoutPosition = layoutPosition;
    }


    protected Term(Parcel in) {
        term = in.readString();
        definition = in.readString();
        layoutPosition = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(term);
        dest.writeString(definition);
        dest.writeInt(layoutPosition);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Term> CREATOR = new Parcelable.Creator<Term>() {
        @Override
        public Term createFromParcel(Parcel in) {
            return new Term(in);
        }

        @Override
        public Term[] newArray(int size) {
            return new Term[size];
        }
    };
}