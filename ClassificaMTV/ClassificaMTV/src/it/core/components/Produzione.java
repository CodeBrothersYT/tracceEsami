package it.core.components;

//La dichiarazione sta già violando l'incapsulamento mostrando in anticipo
//le implementazioni possibili, ma per casi molto semplici ci sta...
//Sealed-Permits significa soltanto che non potranno essere usate altre classi
//se non quelle permesse, per creare delle produzioni.
//(Vedremo la loro utilità anche nelle switch expressions)
public sealed interface Produzione permits Disco, Singolo{
    String getArtistaOBand();
    short getAnno();
    GenereMusicale getGenereMusicale();
}
