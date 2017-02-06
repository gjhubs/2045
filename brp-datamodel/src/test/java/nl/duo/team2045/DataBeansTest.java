package nl.duo.team2045;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanEquals;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanHashCode;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanToString;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.code.beanmatchers.BeanMatchers;
import com.google.code.beanmatchers.ValueGenerator;

/**
 * Test whether the datamodel classes are proper beans.
 *
 * @author InnoValor (software@innovalor.nl)
 * @version 1.21.0
 * @since 0.0.1
 */
public final class DataBeansTest {

   /**
    * List of classes to test, add new classes to include in the test here
    */
   private static final String[] packageNames = { "nl.innovalor.logging.dataimpl" };
   private static final String[] filteredClassNames = { 
		   //add fully qualified classnames for classes not to test here.
   };

   private static final String[] filteredClassNamesForToString = { //
		 //add fully qualified classnames for classes not to test with toString here.
   };

   private static final String[] filteredPackageNames = { //
         "nl.innovalor.logging.dataimpl.typewrappers" };

   private static final String[] filteredPackageNamesForToString = { //
         "nl.innovalor.logging.dataimpl.typewrappers" };
   private static final List<Class<?>> classes = new ArrayList<>();
   private static final List<Class<?>> classesForToString = new ArrayList<>();

   @Before
   public void initTest() {
	   	// add code to ben done for every test here.
   }

   /**
    * Scans all classes accessible from the context class loader which belong to the given packages and subpackages.
    * 
    * @param packageNamesValue
    *           list of packageNames to scan for classes
    * @param acc
    * @throws ClassNotFoundException
    * @throws IOException
    */
   private static void accumulateClasses(final String[] packageNamesValue, final String[] packageNamesNotToAdd, final String[] classNamesNotToAdd, final List<Class<?>> acc) throws ClassNotFoundException, IOException {
      final List<String> cnFilter = new ArrayList<>(Arrays.asList(classNamesNotToAdd));
      final List<String> pnFilter = new ArrayList<>(Arrays.asList(packageNamesNotToAdd));

      for (final String packageName : packageNamesValue) {
         accumulateClasses(packageName, pnFilter, cnFilter, acc);
      }
   }

   /**
    * Scans all classes accessible from the context class loader which belong to the given package and subpackages.
    *
    * @param packageName
    *           The base package
    * @param classNamesNotToAdd
    *           The list of class names not to add.
    * @param acc
    *           The list to accumulate the classes in
    * @throws ClassNotFoundException
    * @throws IOException
    */
   private static void accumulateClasses(final String packageName, final List<String> packageNamesNotToAdd, final List<String> classNamesNotToAdd, final List<Class<?>> acc) throws ClassNotFoundException, IOException {
      final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
      assert classLoader != null;
      final String path = packageName.replace('.', '/');
      final Enumeration<URL> resources = classLoader.getResources(path);
      final List<File> dirs = new ArrayList<>();
      while (resources.hasMoreElements()) {
         final URL resource = resources.nextElement();
         dirs.add(new File(resource.getFile()));
      }

      for (final File directory : dirs) {
         accumulateClasses(directory, packageName, packageNamesNotToAdd, classNamesNotToAdd, acc);
      }

   }

   /**
    * Recursive method used to find all classes in a given directory and subdirs.
    *
    * @param directory
    *           The base directory
    * @param packageName
    *           The package name for classes found inside the base directory
    * @param classNamesNotToAdd
    *           The list of class names not to add.
    * @param acc
    *           The list to accumulate the classes in.
    * @throws ClassNotFoundException
    */
   private static void accumulateClasses(final File directory, final String packageName, final List<String> packageNamesNotToAdd, final List<String> classNamesNotToAdd, final List<Class<?>> acc)
         throws ClassNotFoundException {
      if (!directory.exists()) {
         return;
      }
      final File[] files = directory.listFiles();
      for (final File file : files) {
         if (file.isDirectory()) {
            assert !file.getName().contains(".");
            accumulateClasses(file, packageName + "." + file.getName(), packageNamesNotToAdd, classNamesNotToAdd, acc);
         } else if (file.getName().endsWith(".class") && !packageNamesNotToAdd.contains(packageName)) {
            final String className = packageName + '.' + file.getName().substring(0, file.getName().length() - 6);

            if (!className.contains("$") && !classNamesNotToAdd.contains(className)) {
               acc.add(Class.forName(className));
            }
         }
      }
      return;
   }

   /**
    * Setup before running any test
    */
   @BeforeClass
   public static void setUpBeforeClass() {
      // Registers a UUID generator
      BeanMatchers.registerValueGenerator(new ValueGenerator<UUID>() {

         @Override
         public UUID generate() {
            return UUID.randomUUID();
         }
      }, UUID.class);

      //REMARK: add custom generators here

      try {
         accumulateClasses(packageNames, filteredPackageNames, filteredClassNames, classes);
         accumulateClasses(packageNames, filteredPackageNamesForToString, filteredClassNamesForToString, classesForToString);
      } catch (final ClassNotFoundException e) {
         e.printStackTrace();
      } catch (final IOException e) {
         e.printStackTrace();
      }

   }

   /**
    * Test whether the bean's equals method takes into account every property.
    */
   @SuppressWarnings("static-method")
   @Test
   public void testHaveValidEquals() {
      for (final Class<?> clazz : classes) {
         try {
            assertThat(clazz, hasValidBeanEquals());
         } catch (final Exception ex) {
            System.err.println(String.format("Problem in class %s: %s", clazz.getCanonicalName(), ex));
            throw ex;
         }
      }
   }

   /**
    * Test whether the bean's hashcode() function uses every property in calculating the hashCode.
    */
   @SuppressWarnings("static-method")
   @Test
   public void testHaveValidHashCode() {
      for (final Class<?> clazz : classes) {
         try {
            assertThat(clazz, hasValidBeanHashCode());
         } catch (final Exception ex) {
            System.err.println(String.format("Problem in class %s: %s", clazz.getCanonicalName(), ex));
            throw ex;
         }
      }
   }

   /**
    * Test whether the bean has Valid getters and setters
    */
   @SuppressWarnings("static-method")
   @Test
   public void testHaveValidGettersAndSetters() {
      for (final Class<?> clazz : classes) {
         try {
            assertThat(clazz, hasValidGettersAndSetters());
         } catch (final Exception ex) {
            System.err.println(String.format("Problem in class %s: %s", clazz.getCanonicalName(), ex));
            throw ex;
         }
      }
   }

   /**
    * Tests whether the bean has a default constructor.
    */
   @SuppressWarnings("static-method")
   @Test
   public void testHaveValidBeanConstructor() {
      for (final Class<?> clazz : classes) {
         try {
            assertThat(clazz, hasValidBeanConstructor());
         } catch (final Exception ex) {
            System.err.println(String.format("Problem in class %s: %s", clazz.getCanonicalName(), ex));
            throw ex;
         }
      }
   }

   /**
    * Tests whether the bean has a default constructor.
    */
   @SuppressWarnings("static-method")
   @Test
   public void testHaveValidToString() {
      for (final Class<?> clazz : classesForToString) {
         try {
            assertThat(clazz, hasValidBeanToString());
         } catch (final Exception ex) {
            System.err.println(String.format("Problem in class %s: %s", clazz.getCanonicalName(), ex));
            ex.printStackTrace(System.err);
            throw ex;
         }
      }
   }

}
