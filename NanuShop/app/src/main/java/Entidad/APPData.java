package Entidad;

public class APPData {
    private static APPData instance;

    private int userId;

    private APPData() {
        // Constructor privado para evitar la creación de instancias directas
    }

    public static synchronized APPData getInstance() {
        if (instance == null) {
            instance = new APPData();
        }
        return instance;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
