package com.example.weatherappmvp.model;

public class WeatherForecastModel {

    private java.util.List<List> list;

    public java.util.List<List> getList() {
        return list;
    }

    public void setList(java.util.List<List> value) {
        this.list = value;
    }

    public class List {
        private MainClass main;
        private java.util.List<Weather> weather;
        private String dt_txt;

        public MainClass getMain() {
            return main;
        }

        public void setMain(MainClass value) {
            this.main = value;
        }

        public java.util.List<Weather> getWeather() {
            return weather;
        }

        public void setWeather(java.util.List<Weather> value) {
            this.weather = value;
        }

        public String getDtTxt() {
            return dt_txt;
        }

        public void setDtTxt(String value) {
            this.dt_txt = value;
        }


        public class MainClass {
            private double temp;
            private double temp_min;
            private double temp_max;

            public double getTemp() {
                return temp;
            }

            public void setTemp(double value) {
                this.temp = value;
            }

            public double getTempMin() {
                return temp_min;
            }

            public void setTempMin(double value) {
                this.temp_min = value;
            }

            public double getTempMax() {
                return temp_max;
            }

            public void setTempMax(double value) {
                this.temp_max = value;
            }
        }

        public class Weather {
            private String icon;

            public String getIcon() { return icon; }
            public void setIcon(String value) { this.icon = value; }
        }
    }
}
