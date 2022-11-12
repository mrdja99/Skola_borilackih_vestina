/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niti;


import domen.Kurs;
import domen.OpstiDomenskiObjekat;
import domen.Polaznik;
import domen.StavkaZakazivanjaTreninga;
import domen.Trener;
import domen.Trening;
import domen.ZakazivanjeTreninga;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import komunikacija.KlijentskiZahtev;
import komunikacija.ServerskiOdgovor;
import kontroler.Kontroler;
import operacije.Operacije;

/**
 *
 * @author User
 */
public class KlijentskaNit extends Thread{
    
    private Socket s;
    private List<KlijentskaNit> klijenti;
    private Trener trener;
    private boolean kraj;

    public KlijentskaNit(Socket s, List<KlijentskaNit> klijenti) {
        this.s = s;
        this.klijenti = klijenti;
    }

    @Override
    public void run() {
        while(!kraj) {
            try {
                System.out.println("Ceka se klijentski zahtev...");
                KlijentskiZahtev kz = primiZahtev();
                ServerskiOdgovor so = new ServerskiOdgovor();
                switch(kz.getOperacija()) {
                    case Operacije.LOGIN:
                        try {
                            trener = (Trener) kz.getParametar();
                            List<Trener> listaTrenera = (List<Trener>) Kontroler.getInstance().pronadjiTrenera((OpstiDomenskiObjekat) trener);
                            if(listaTrenera.isEmpty()) {
                                throw new Exception();
                            }
                            for(int i=0; i< listaTrenera.size(); i++) {
                                Trener tr = listaTrenera.get(i);
                                if((trener.getKorisnickoIme().equals(tr.getKorisnickoIme()) && trener.getLozinka().equals(tr.getLozinka())) && tr.isUlogovan()) {
                                    so.setPoruka("Vec ste ulogovani!");
                                    so.setUspesno(false);
                                }else {
                                    trener.setTrenerId(Kontroler.getInstance().vratiTrenerID((OpstiDomenskiObjekat) kz.getParametar()));
                                    trener.setIme(Kontroler.getInstance().vratiImeTrenera((OpstiDomenskiObjekat) kz.getParametar()));
                                    trener.setPrezime(Kontroler.getInstance().vratiPrezimeTrenera((OpstiDomenskiObjekat) kz.getParametar()));
                                    
                                    Kontroler.getInstance().izmeniTrener((OpstiDomenskiObjekat) kz.getParametar());
                                    trener.setUlogovan(true);
                                    so.setOdgovor(trener);
                                    so.setPoruka("Trener " + trener.toString() + " se uspesno prijavio");
                                    so.setUspesno(true);
                                }
                                
                            }
                        
                        } catch(Exception e) {
                            so.setPoruka("Sistem ne moze da vas prijavi!");
                            so.setUspesno(false);
                            System.out.println("Korisnik nije u bazi!");
                        }
                        break;
                    case Operacije.LOGOUT:
                        try {
                            Kontroler.getInstance().izmeniTrener(trener);
                            kraj = true;
                            so.setUspesno(true);
                            so.setPoruka("Uspesno ste se odjavili");
                        }catch (Exception ex) {
                            so.setUspesno(false);
                            so.setPoruka("Greska prilikom pokusaja odjavljivanja");
                        }
                        break;
                    case Operacije.KREIRAJ_POLAZNIKA:
                        try {
                            Kontroler.getInstance().kreirajPolaznika((OpstiDomenskiObjekat) kz.getParametar());
                            so.setPoruka("Sistem je kreirao polaznika");
                            so.setUspesno(true);
                        }catch (Exception ex) {
                            so.setPoruka("Sistem nе moze da kreira novog polaznika");
                            so.setUspesno(false);
                        }
                        break;
                    case Operacije.KREIRAJ_KURS:
                        try {
                            Kontroler.getInstance().kreirajKurs((OpstiDomenskiObjekat) kz.getParametar());
                            so.setPoruka("Sistem je kreirao novi kurs");
                            so.setUspesno(true);
                        } catch (Exception ex) {
                            so.setPoruka("Sistem ne moze da kreira kurs");
                            so.setUspesno(false);
                        }
                        break;
                    case Operacije.VRATI_LISTU_KURSEVA:
                        try {
                            List<OpstiDomenskiObjekat> kursevi = Kontroler.getInstance().vratiListuKurseva(new Kurs());
                            so.setOdgovor(kursevi);
                            so.setPoruka("Sistem je uspesno ucitao listu kurseva");
                            so.setUspesno(true);
                        } catch (Exception ex) {
                            so.setPoruka("Sistem ne moze da ucita kurseve");
                            so.setUspesno(false);
                        }
                        break;
                    case Operacije.VRATI_IZABRANI_KURS:
                        try {
                            int id = Kontroler.getInstance().vratiIzabranikurs((OpstiDomenskiObjekat) kz.getParametar());
                            so.setOdgovor(id);
                            so.setPoruka("Sistem je ucitao izabrani kurs");
                            so.setUspesno(true);
                        } catch (Exception ex) {
                            so.setPoruka("Sistem ne moze da ucita izabrani kurs");
                            so.setUspesno(true);
                        }
                        break;
                    case Operacije.AZURIRAJ_KURS:
                        try {
                            Kontroler.getInstance().azurirajKurs((OpstiDomenskiObjekat) kz.getParametar());
                            so.setPoruka("Sistem je promenio podatke o kursu");
                            so.setUspesno(true);
                        } catch (Exception ex) {
                            so.setPoruka("Sistem ne moze da promeni podatke o kursu");
                            so.setUspesno(false);
                        }
                        break;
                    case Operacije.OBRISI_KURS:
                        try {
                            Kontroler.getInstance().obrisiKurs((OpstiDomenskiObjekat) kz.getParametar());
                            so.setPoruka("Sistem je obrisao kurs");
                            so.setUspesno(true);
                        } catch (Exception ex) {
                            so.setPoruka("Sistem ne moze da obrise kurs");
                            so.setUspesno(false);
                        }
                        break;
                    case Operacije.VRATI_LISTU_POLAZNIKA:
                        try {
                            ArrayList<OpstiDomenskiObjekat> listaPolaznikia = Kontroler.getInstance().vratiListuPolaznika((OpstiDomenskiObjekat) kz.getParametar());
                            so.setOdgovor(listaPolaznikia);
                            if(((ArrayList<Polaznik>) so.getOdgovor()).isEmpty()){
                                throw new Exception();
                            }
                            so.setPoruka("Sistem je nasao polaznike po zadatoj vrednosti");
                            so.setUspesno(true);
                        } catch (Exception ex) {
                            so.setPoruka("Sistem ne moze da nadje polaznike po zadatoj vrednosti");
                            so.setUspesno(false);
                        }
                        break;
                    case Operacije.VRATI_IZABRANOG_POLAZNIKA:
                        try {
                            int id = Kontroler.getInstance().vratiPolaznika((OpstiDomenskiObjekat) kz.getParametar());
                            so.setOdgovor(id);
                            so.setPoruka("Sistem je uspesno ucitao polaznika");
                            so.setUspesno(true);
                        }catch (Exception ex) {
                            so.setPoruka("Sistem ne moze da ucita izabranog polaznika");
                            so.setUspesno(false);
                        }
                        break;
                    case Operacije.AZURIRAJ_POLAZNIKA:
                        try {
                            Kontroler.getInstance().azurirajPolaznika((OpstiDomenskiObjekat) kz.getParametar());
                            so.setPoruka("Sistem je promenio podatke o polazniku");
                            so.setUspesno(true);
                        } catch (Exception ex) {
                            so.setPoruka("Sistem ne moze da promeni podatke polaznika");
                            so.setUspesno(false);
                        }
                        break;
                    case Operacije.VRATI_LISTU_TRENINGA:
                        try {
                            ArrayList<OpstiDomenskiObjekat> listaTreninga = Kontroler.getInstance().vratiListuTreninga(new Trening());
                            so.setOdgovor(listaTreninga);
                            so.setPoruka("Sistem je uspesno vratio listu treninga");
                            so.setUspesno(true);
                        } catch (Exception ex) {
                            so.setPoruka("Sistem ne moze da vrati listu treninga");
                            so.setUspesno(false);
                        }
                        break;
                    case Operacije.ZAKAZIVANJE_TRENINGA:
                        try {
                            Kontroler.getInstance().zakazivanjeTreninga((OpstiDomenskiObjekat) kz.getParametar());
                            so.setPoruka("Sistem je zakazao treningе");
                            so.setUspesno(true);
                        } catch (Exception ex) {
                            so.setPoruka("Sistem ne moze da zakaze treningе");
                            so.setUspesno(false);
                        }
                        break;
                    case Operacije.POSTOJI_ZAKAZIVANJE:
                        try {
                            boolean postoji = Kontroler.getInstance().postojiZakazivanje((OpstiDomenskiObjekat) kz.getParametar());
                            so.setOdgovor(postoji);
                            so.setPoruka("Vec postoji zakazivanje za ovog polaznika!");
                            so.setUspesno(true);
                        } catch (Exception ex) {
                            so.setPoruka("Ne postoji zakazivanje za ovog polaznika");
                            so.setUspesno(false);
                        }
                        break;
                    case Operacije.VRATI_ZAKAZANE_TRENINGE:
                        try {
                            ArrayList<OpstiDomenskiObjekat> listaTreninga = Kontroler.getInstance().vratiZakazivanja((OpstiDomenskiObjekat) kz.getParametar());
                            so.setOdgovor(listaTreninga);
                            if(((ArrayList<ZakazivanjeTreninga>) so.getOdgovor()).isEmpty()){
                                throw new Exception();
                            }
                            so.setPoruka("Sistem je nasao zakazane treninge po zadatoj vrednosti");
                            so.setUspesno(true);
                        } catch (Exception ex) {
                            so.setPoruka("Sistem ne moze da nadje zakazane treninge po zadatoj vrednosti");
                            so.setUspesno(false);
                        }
                        break;
                    case Operacije.VRATI_ZAKAZIVANJE:
                        try {
                            int id = Kontroler.getInstance().pronadjiZakazivanje((OpstiDomenskiObjekat) kz.getParametar());
                            so.setOdgovor(id);
                            so.setPoruka("Sistem je ucitao odabrano zakazivanje treninga!");
                            so.setUspesno(true);
                        } catch (Exception ex) {
                            so.setPoruka("Sistem ne moze da ucita zakazivanje treninga!");
                            so.setUspesno(false);
                        }
                        break;
                    case Operacije.VRATI_LISTU_STAVKI:
                        try {
                            ZakazivanjeTreninga zakazivanje = (ZakazivanjeTreninga) kz.getParametar();
                            StavkaZakazivanjaTreninga szp = new StavkaZakazivanjaTreninga();
                            szp.setZakazivanjeTreninga(zakazivanje);
                            ArrayList<OpstiDomenskiObjekat> stavke = Kontroler.getInstance().vratiListuStavki((OpstiDomenskiObjekat)szp);
                            so.setOdgovor(stavke);
                            so.setPoruka("Sistem je uspesno ucitao stavke");
                            so.setUspesno(true);
                        }catch(Exception ex){
                            so.setPoruka("Sistem ne moze da ucita stavke");
                            so.setUspesno(false);
                        }
                        break;
                    case Operacije.SACUVAJ_IZMENE_ZAKAZIVANJA:
                        try {
                            Kontroler.getInstance().promeniPodatkeZakazivanje((OpstiDomenskiObjekat) kz.getParametar());
                            so.setPoruka("Sistem je uspesno promenio podatke zakazivanja treninga");
                            so.setUspesno(true);
                        } catch(Exception ex) {
                            so.setPoruka("Sistem ne moze da promeni podatke zakazivanja treninga");
                            so.setUspesno(false);
                        }
                        break;
                    case Operacije.SACUVAJ_STAVKU:
                        try {
                            Kontroler.getInstance().sacuvajStavku((OpstiDomenskiObjekat) kz.getParametar());
                            so.setPoruka("Sistem je uspesno sacuvao stavku");
                            so.setUspesno(true);
                        }catch(Exception ex){
                            so.setPoruka("Sistem nije uspeo da sacuva stavku");
                            so.setUspesno(false);
                        }
                        break;
                    case Operacije.IZBRISI_STAVKU:
                        try {
                            Kontroler.getInstance().izbrisiStavku((OpstiDomenskiObjekat) kz.getParametar());
                            so.setPoruka("Sistem je uspesno izbrisao stavku zakazivanja!");
                            so.setUspesno(true);
                        }catch(Exception ex){
                            so.setPoruka("Sistem nije uspeo da izbrise stavku zakazivanja!");
                            so.setUspesno(false);
                        }
                        break;
                    case Operacije.OTKAZI:
                        try {
                            Kontroler.getInstance().otkaizivanje((OpstiDomenskiObjekat) kz.getParametar());
                            so.setPoruka("Sistem je obrisao podatke o zakazivanju treninga!");
                            so.setUspesno(true);
                        }catch(Exception ex){
                            so.setPoruka("Sistem ne moze da obrise podatke o zakazivanju treninga!");
                            so.setUspesno(false);
                        }
                        break;
                    case Operacije.VRATI_KURSEVE_POLAZNIKA:
                        try {
                            //int id = Kontroler.getInstance().vratiPolaznika((OpstiDomenskiObjekat) kz.getParametar());
                            List<OpstiDomenskiObjekat> kurseviPolaznika = Kontroler.getInstance().vratiKursevePolaznika(new Kurs(), (OpstiDomenskiObjekat) kz.getParametar());
                            so.setOdgovor(kurseviPolaznika);
                            so.setPoruka("Sistem je uspesno ucitao listu kurseva");
                            so.setUspesno(true);
                        } catch (Exception ex) {
                            so.setPoruka("Sistem ne moze da ucita kurseve");
                            so.setUspesno(false);
                        }
                        break;
                }
                posaljiOdgovor(so);
            } catch (Exception ex) {
                Logger.getLogger(KlijentskaNit.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private KlijentskiZahtev primiZahtev() {
        try {
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            return (KlijentskiZahtev) ois.readObject();
        } catch (IOException ex) {
            Logger.getLogger(KlijentskaNit.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(KlijentskaNit.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private void posaljiOdgovor(ServerskiOdgovor so) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(so);
        } catch (IOException ex) {
            Logger.getLogger(KlijentskaNit.class.getName()).log(Level.SEVERE, null, ex);
        }
          
    }

    public Socket getS() {
        return s;
    }

    public void setS(Socket s) {
        this.s = s;
    }

    
    
    
    
    
    
}
