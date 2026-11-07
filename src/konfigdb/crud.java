package konfigdb;

import java.sql.*;
import javax.swing.JOptionPane;
import masyarakat.Keluarga;
import masyarakat.Warga;
import masyarakat.MutasiWarga;

public class crud {

    private Connection koneksiDB;
    private String username = "root";
    private String password = "";
    private String jdbcurl
            = "jdbc:mysql://localhost:3306/masyarakat_2410010266";

    public crud() {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            koneksiDB = DriverManager.getConnection(
                    jdbcurl, username, password
            );

            System.out.println("Database terkoneksi");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    null, "Koneksi gagal: " + e.getMessage()
            );
        }
    }

    // Mengambil koneksi untuk kebutuhan tampil data
    public Connection getKoneksiDB() {
        return koneksiDB;
    }

    // =====================================
    // CRUD TABEL KELUARGA
    // =====================================
    public void simpanKeluarga(Keluarga k) {
        String sql = "INSERT INTO keluarga "
                + "(id_keluarga, kepala_keluarga, alamat, dusun, rt, rw, ekonomi) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = koneksiDB.prepareStatement(sql);

            ps.setString(1, k.getIdKeluarga());
            ps.setString(2, k.getKepalaKeluarga());
            ps.setString(3, k.getAlamat());
            ps.setString(4, k.getDusun());
            ps.setString(5, k.getRt());
            ps.setString(6, k.getRw());
            ps.setString(7, k.getEkonomi());

            int hasil = ps.executeUpdate();

            JOptionPane.showMessageDialog(null,
                    "Jumlah data yang berhasil disimpan: " + hasil);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void ubahKeluarga(Keluarga k) {
        String sql = "UPDATE keluarga SET kepala_keluarga=?, "
                + "alamat=?, dusun=?, rt=?, rw=?, ekonomi=? "
                + "WHERE id_keluarga=?";

        try {
            PreparedStatement ps = koneksiDB.prepareStatement(sql);

            ps.setString(1, k.getKepalaKeluarga());
            ps.setString(2, k.getAlamat());
            ps.setString(3, k.getDusun());
            ps.setString(4, k.getRt());
            ps.setString(5, k.getRw());
            ps.setString(6, k.getEkonomi());
            ps.setString(7, k.getIdKeluarga());

            int hasil = ps.executeUpdate();

            JOptionPane.showMessageDialog(null,
                    hasil > 0 ? "Data keluarga berhasil diubah!"
                            : "Data keluarga tidak ditemukan!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void hapusKeluarga(String id) {
        String sql = "DELETE FROM keluarga WHERE id_keluarga=?";

        try {
            PreparedStatement ps = koneksiDB.prepareStatement(sql);
            ps.setString(1, id);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(null,
                    "Data keluarga berhasil dihapus!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    // =====================================
    // CRUD TABEL WARGA
    // =====================================
    public void simpanWarga(Warga w) {
        try {
            String sql = "INSERT INTO warga "
                    + "(no_ktp, id_keluarga, nama, agama, t_lahir, "
                    + "tgl_lahir, j_kel, gol_darah, w_negara, "
                    + "pendidikan, pekerjaan, s_nikah, status) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = koneksiDB.prepareStatement(sql);

            ps.setString(1, w.getNoKtp());
            ps.setString(2, w.getIdKeluarga());
            ps.setString(3, w.getNama());
            ps.setString(4, w.getAgama());
            ps.setString(5, w.getTempatLahir());
            ps.setDate(6, java.sql.Date.valueOf(w.getTanggalLahir()));
            ps.setString(7, w.getJenisKelamin());
            ps.setString(8, w.getGolDarah());
            ps.setString(9, w.getWargaNegara());
            ps.setString(10, w.getPendidikan());
            ps.setString(11, w.getPekerjaan());
            ps.setString(12, w.getStatusNikah());
            ps.setString(13, w.getStatus());

            ps.executeUpdate();

            JOptionPane.showMessageDialog(null,
                    "Data warga berhasil disimpan!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Gagal menyimpan data: " + e.getMessage());
        }
    }

    public void ubahWarga(Warga w) {
        try {
            String sql = "UPDATE warga SET "
                    + "id_keluarga=?, nama=?, agama=?, t_lahir=?, "
                    + "tgl_lahir=?, j_kel=?, gol_darah=?, w_negara=?, "
                    + "pendidikan=?, pekerjaan=?, s_nikah=?, status=? "
                    + "WHERE no_ktp=?";

            PreparedStatement ps = koneksiDB.prepareStatement(sql);

            ps.setString(1, w.getIdKeluarga());
            ps.setString(2, w.getNama());
            ps.setString(3, w.getAgama());
            ps.setString(4, w.getTempatLahir());
            ps.setDate(5, java.sql.Date.valueOf(w.getTanggalLahir()));
            ps.setString(6, w.getJenisKelamin());
            ps.setString(7, w.getGolDarah());
            ps.setString(8, w.getWargaNegara());
            ps.setString(9, w.getPendidikan());
            ps.setString(10, w.getPekerjaan());
            ps.setString(11, w.getStatusNikah());
            ps.setString(12, w.getStatus());
            ps.setString(13, w.getNoKtp());

            int hasil = ps.executeUpdate();

            if (hasil > 0) {
                JOptionPane.showMessageDialog(null,
                        "Data warga berhasil diubah!");
            } else {
                JOptionPane.showMessageDialog(null,
                        "Data warga tidak ditemukan!");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Gagal mengubah data: " + e.getMessage());
        }
    }

    public void hapusWarga(String noKtp) {
        try {
            String sql = "DELETE FROM warga WHERE no_ktp = ?";
            PreparedStatement ps = koneksiDB.prepareStatement(sql);
            ps.setString(1, noKtp);

            int hasil = ps.executeUpdate();

            if (hasil > 0) {
                JOptionPane.showMessageDialog(null,
                        "Data warga berhasil dihapus!");
            } else {
                JOptionPane.showMessageDialog(null,
                        "Data warga tidak ditemukan!");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Gagal menghapus data: " + e.getMessage());
        }
    }

    // =====================================
    // CRUD TABEL MUTASI WARGA
    // =====================================
    public void simpanMutasi(MutasiWarga m) {
        try {
            String sql = "INSERT INTO mutasi_warga "
                    + "(id_warga, jenis_mutasi, tanggal, keterangan) "
                    + "VALUES (?, ?, ?, ?)";

            PreparedStatement ps = koneksiDB.prepareStatement(sql);

            ps.setString(1, m.getIdWarga());
            ps.setString(2, m.getJenisMutasi());
            ps.setDate(3, java.sql.Date.valueOf(m.getTanggal()));
            ps.setString(4, m.getKeterangan());

            ps.executeUpdate();

            JOptionPane.showMessageDialog(null,
                    "Data mutasi berhasil disimpan!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Gagal menyimpan data: " + e.getMessage());
        }
    }

    public void ubahMutasi(MutasiWarga m) {
        try {
            String sql = "UPDATE mutasi_warga SET "
                    + "id_warga=?, jenis_mutasi=?, tanggal=?, keterangan=? "
                    + "WHERE id_mutasi=?";

            PreparedStatement ps = koneksiDB.prepareStatement(sql);

            ps.setString(1, m.getIdWarga());
            ps.setString(2, m.getJenisMutasi());
            ps.setDate(3, java.sql.Date.valueOf(m.getTanggal()));
            ps.setString(4, m.getKeterangan());
            ps.setInt(5, m.getIdMutasi());

            int hasil = ps.executeUpdate();

            if (hasil > 0) {
                JOptionPane.showMessageDialog(null,
                        "Data mutasi berhasil diubah!");
            } else {
                JOptionPane.showMessageDialog(null,
                        "Data mutasi tidak ditemukan!");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Gagal mengubah data: " + e.getMessage());
        }
    }

    public void hapusMutasi(int id) {
        try {
            String sql = "DELETE FROM mutasi_warga WHERE id_mutasi = ?";
            PreparedStatement ps = koneksiDB.prepareStatement(sql);

            ps.setInt(1, id);

            int hasil = ps.executeUpdate();

            if (hasil > 0) {
                JOptionPane.showMessageDialog(null,
                        "Data mutasi berhasil dihapus!");
            } else {
                JOptionPane.showMessageDialog(null,
                        "Data mutasi tidak ditemukan!");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Gagal menghapus data: " + e.getMessage());
        }
    }

    public Keluarga cariKeluarga(String id) {
        Keluarga k = null;

        try {
            String sql = "SELECT * FROM keluarga WHERE id_keluarga = ?";
            PreparedStatement ps = koneksiDB.prepareStatement(sql);

            ps.setString(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                k = new Keluarga();

                k.setIdKeluarga(rs.getString("id_keluarga"));
                k.setKepalaKeluarga(rs.getString("kepala_keluarga"));
                k.setAlamat(rs.getString("alamat"));
                k.setDusun(rs.getString("dusun"));
                k.setRt(rs.getString("rt"));
                k.setRw(rs.getString("rw"));
                k.setEkonomi(rs.getString("ekonomi"));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Gagal mencari data: " + e.getMessage());
        }

        return k;
    }

    public Warga cariWarga(String noKtp) {
        Warga w = null;

        try {
            String sql = "SELECT * FROM warga WHERE no_ktp = ?";
            PreparedStatement ps = koneksiDB.prepareStatement(sql);
            ps.setString(1, noKtp);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                w = new Warga();

                w.setNoKtp(rs.getString("no_ktp"));
                w.setIdKeluarga(rs.getString("id_keluarga"));
                w.setNama(rs.getString("nama"));
                w.setAgama(rs.getString("agama"));
                w.setTempatLahir(rs.getString("t_lahir"));
                w.setTanggalLahir(rs.getString("tgl_lahir"));
                w.setJenisKelamin(rs.getString("j_kel"));
                w.setGolDarah(rs.getString("gol_darah"));
                w.setWargaNegara(rs.getString("w_negara"));
                w.setPendidikan(rs.getString("pendidikan"));
                w.setPekerjaan(rs.getString("pekerjaan"));
                w.setStatusNikah(rs.getString("s_nikah"));
                w.setStatus(rs.getString("status"));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Gagal mencari data: " + e.getMessage());
        }

        return w;
    }

    public MutasiWarga cariMutasi(int id) {
        MutasiWarga m = null;

        try {
            String sql = "SELECT * FROM mutasi_warga WHERE id_mutasi = ?";
            PreparedStatement ps = koneksiDB.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                m = new MutasiWarga();

                m.setIdMutasi(rs.getInt("id_mutasi"));
                m.setIdWarga(rs.getString("id_warga"));
                m.setJenisMutasi(rs.getString("jenis_mutasi"));
                m.setTanggal(rs.getString("tanggal"));
                m.setKeterangan(rs.getString("keterangan"));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Gagal mencari data: " + e.getMessage());
        }

        return m;
    }
}
