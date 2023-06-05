public class Actions {
    private boolean rename;
    private boolean importData;
    private boolean delete;

    public Actions(boolean rename, boolean importData, boolean delete) {
        this.rename = rename;
        this.importData = importData;
        this.delete = delete;
    }

    public boolean isRename() {
        return rename;
    }

    public void setRename(boolean rename) {
        this.rename = rename;
    }

    public boolean isImportData() {
        return importData;
    }

    public void setImportData(boolean importData) {
        this.importData = importData;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }
}
