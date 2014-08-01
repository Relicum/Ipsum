package com.relicum.ipsum.Items;

import org.bukkit.enchantments.Enchantment;

/**
 * Name: TestBuilder.java Created: 01 August 2014
 *
 * @author Relicum
 * @version 0.0.1
 */
public class TestBuilder {
    private Enchantment enchantment;
    private int level;
    private boolean force;
    private String name;
    private int age;
    protected String exclude;

    TestBuilder(Enchantment enchantment, int level, boolean force, String name, int age, String exclude) {
        this.enchantment = enchantment;
        this.level = level;
        this.force = force;
        this.name = name;
        this.age = age;
        this.exclude = exclude;
    }

    public static class TestBuilderBuilder {
        private Enchantment enchantment;
        private int level;
        private boolean force;
        private String name;
        private int age;
        private String exclude;

        public TestBuilderBuilder enchantment(Enchantment enchantment) {
            this.enchantment = enchantment;
            return this;
        }

        public TestBuilderBuilder level(int level) {
            this.level = level;
            return this;
        }

        public TestBuilderBuilder force(boolean force) {
            this.force = force;
            return this;
        }

        public TestBuilderBuilder name(String name) {
            this.name = name;
            return this;
        }

        public TestBuilderBuilder age(int age) {
            this.age = age;
            return this;
        }

        public TestBuilderBuilder exclude(String exclude) {
            this.exclude = exclude;
            return this;
        }

        public TestBuilder build() {
            return new TestBuilder(enchantment, level, force, name, age, exclude);
        }

        public String toString() {
            return "TestBuilder.TestBuilderBuilder(enchantment=" + enchantment + ", level=" + level + ", force=" + force + ", name=" + name + ", age=" + age + ", exclude=" + exclude + ")";
        }

    }
}
