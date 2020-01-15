package com.example.weatherappmvp.model;

public class WeatherModel {

    private java.util.List<List> list;

    public java.util.List<List> getList() { return list; }
    public void setList(java.util.List<List> value) { this.list = value; }

    public class List {
        private java.util.List<Weather> weather;
        private Main main;
        private long dt;
        private long id;
        private String name;

        public java.util.List<Weather> getWeather() { return weather; }
        public void setWeather(java.util.List<Weather> value) { this.weather = value; }

        public Main getMain() { return main; }
        public void setMain(Main value) { this.main = value; }

        public long getDt() { return dt; }
        public void setDt(long value) { this.dt = value; }

        public long getID() { return id; }
        public void setID(long value) { this.id = value; }

        public String getName() { return name; }
        public void setName(String value) { this.name = value; }

        public class Main {
            private double temp;
            private long tempMin;
            private long tempMax;

            public double getTemp() { return temp; }
            public void setTemp(double value) { this.temp = value; }

            public long getTempMin() { return tempMin; }
            public void setTempMin(long value) { this.tempMin = value; }

            public long getTempMax() { return tempMax; }
            public void setTempMax(long value) { this.tempMax = value; }
        }

        public class Weather {
            private String icon;

            public String getIcon() { return icon; }
            public void setIcon(String value) { this.icon = value; }
        }
    }
}
