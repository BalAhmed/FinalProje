package tr.edu.yazilimaraclari.BalMarket;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/urun")
public class UrunKontrol {

	public record Urun(String urunAdi, double fiyat, String urunNo) {
        public Object getUrunAdi() {
            return urunAdi;
        }

        public Double getFiyat() {
            return fiyat;
        }
 
        public Object getUrunNo() {
            return urunNo;
        }
    }
    private static final List<Urun> URUN_LISTESI = new ArrayList<>();
    static {
        URUN_LISTESI.add(new Urun("makarna", 20.0, "1"));
        URUN_LISTESI.add(new Urun("pirinç", 57.0, "2"));
        URUN_LISTESI.add(new Urun("et", 235.0, "3"));
    }

    @GetMapping("/")
    public ResponseEntity<List<Urun>> urunListele() {
        return ResponseEntity.ok(URUN_LISTESI);
    }

    @GetMapping("/{urunNo}")
    public Urun bul(@PathVariable String urunNo) {
        for (Urun urun : URUN_LISTESI) {
            if (urun.getUrunNo().equals(urunNo)) {
                return urun;
            }
        }
        return null;
    }

    @PostMapping("/ekle")
    public static Urun ekle(@RequestBody Urun urun) {
        URUN_LISTESI.add(urun);
        return urun;
    }

    @DeleteMapping("/sil/{urunNo}")
    public ResponseEntity<String> urunSil(@PathVariable String urunNo) {
        try {
            Urun urun = bul(urunNo);

            if (urun != null) {
                URUN_LISTESI.remove(urun);
                return new ResponseEntity<>("Ürün başarıyla silindi", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Ürün bulunamadı", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("İşlem sırasında bir hata oluştu", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public List<Urun> getUrunListesi() {
        return URUN_LISTESI;
    }

	public static Object urunSil(Urun urun) {

		return null;
	}
}