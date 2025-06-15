package com.juancarlos.cvportafolio;

    public class Item {
        private int id;
        private String imgPath;
        private boolean isBlog;
        private String title;
        private String description;
        private String ghLink;
        private String demoLink;

        // Constructor
        public Item(int id, String imgPath, boolean isBlog, String title,
                    String description, String ghLink, String demoLink) {
            this.id = id;
            this.imgPath = imgPath;
            this.isBlog = isBlog;
            this.title = title;
            this.description = description;
            this.ghLink = ghLink;
            this.demoLink = demoLink;
        }

        // Getters
        public int getId() { return id; }
        public String getImgPath() { return imgPath; }
        public boolean isBlog() { return isBlog; }
        public String getTitle() { return title; }
        public String getDescription() { return description; }
        public String getGhLink() { return ghLink; }
        public String getDemoLink() { return demoLink; }
    }

