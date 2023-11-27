package Entidad;

public class Productos {
    private int id ,Valor_producto;
    private String nombre_producto , Des_producto , Disponible , img ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getValor_producto() {
        return Valor_producto;
    }

    public void setValor_producto(int valor_producto) {
        Valor_producto = valor_producto;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public String getDes_producto() {
        return Des_producto;
    }

    public void setDes_producto(String des_producto) {
        Des_producto = des_producto;
    }

    public String getDisponible() {
        return Disponible;
    }

    public void setDisponible(String disponible) {
        Disponible = disponible;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
