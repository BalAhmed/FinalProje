package tr.edu.yazilimaraclari.BalMarket;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import tr.edu.yazilimaraclari.BalMarket.UrunKontrol.Urun;

public class UrunKontrolTest {

	 public void urunListeleTest() {
	        UrunKontrol urunKontrol = new UrunKontrol();
	        ResponseEntity<List<UrunKontrol.Urun>> responseEntity = urunKontrol.urunListele();
	        List<UrunKontrol.Urun> urunListesi = responseEntity.getBody();

	        Assertions.assertEquals(3, urunListesi.size());
	        Assertions.assertEquals("makarna", urunListesi.get(0).getUrunAdi());
	        Assertions.assertEquals("pirinç", urunListesi.get(1).getUrunAdi());
	        Assertions.assertEquals("et", urunListesi.get(2).getUrunAdi());
	    }


    @Test
    public void bulTest() {
        UrunKontrol urunKontrol = new UrunKontrol();
        Urun urun = urunKontrol.bul("1");
        Assertions.assertEquals("makarna", urun.getUrunAdi());
        Assertions.assertEquals(20.0, urun.getFiyat());
    }

    @Test
    public void urunEkleTest() {
        UrunKontrol urunKontrol = new UrunKontrol();
        Urun urun = new Urun("makarna", 20.0, "1");
        boolean urunEklendi = UrunKontrol.ekle(urun) != null;
        List<Urun> urunListesi = urunKontrol.getUrunListesi();
        Assertions.assertTrue(urunEklendi);
        Assertions.assertEquals(4, urunListesi.size());
        Assertions.assertEquals("makarna", urunListesi.get(3).getUrunAdi());
    }

    @Test
    public void urunSilTest() {
        UrunKontrol urunKontrol = new UrunKontrol();

        ResponseEntity<String> responseEntity = urunKontrol.urunSil("1");
        boolean urunSilindi = responseEntity.getStatusCode() == HttpStatus.OK;
        List<UrunKontrol.Urun> urunListesi = urunKontrol.getUrunListesi();
        Assertions.assertTrue(urunSilindi);
        Assertions.assertEquals(3, urunListesi.size());
    }
}