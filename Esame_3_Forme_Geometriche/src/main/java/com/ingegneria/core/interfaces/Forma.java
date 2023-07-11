package com.ingegneria.core.interfaces;

import com.ingegneria.core.utility.RaggruppatoreDiForme;

public interface Forma {
    String getColore();
    int getDimensione();
    void accetta ( RaggruppatoreDiForme raggruppatore );
}
