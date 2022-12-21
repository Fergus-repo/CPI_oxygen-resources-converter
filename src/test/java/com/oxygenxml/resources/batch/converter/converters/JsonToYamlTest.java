package com.oxygenxml.resources.batch.converter.converters;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.TransformerException;

import org.junit.Test;

import com.oxygenxml.batch.converter.core.ConverterTypes;
import com.oxygenxml.batch.converter.core.extensions.FileExtensionType;
import com.oxygenxml.batch.converter.core.transformer.TransformerFactoryCreator;
import com.oxygenxml.batch.converter.core.utils.ConverterFileUtils;
import com.oxygenxml.resources.batch.converter.BatchConverter;
import com.oxygenxml.resources.batch.converter.BatchConverterImpl;
import com.oxygenxml.resources.batch.converter.UserInputsProvider;
import com.oxygenxml.resources.batch.converter.reporter.ProblemReporter;

import ro.sync.exml.options.APIAccessibleOptionTags;
import ro.sync.exml.options.Options;
import tests.utils.ConverterStatusReporterTestImpl;
import tests.utils.ConvertorWorkerInteractorTestImpl;
import tests.utils.FileComparationUtil;
import tests.utils.ProblemReporterTestImpl;
import tests.utils.StatusReporterImpl;
import tests.utils.TransformerFactoryCreatorImpl;

/**
 * Test for JSON to YAML conversion.
 */
public class JsonToYamlTest {
  
  @Test
  public void test() throws TransformerException, IOException {
  
    File sample  = new File("test-sample/jsonTest.json");   
    File goodSample = new File("test-sample/yamlTest.yaml");
    final File outputFolder = new File(sample.getParentFile(), "out");
    
    TransformerFactoryCreator transformerCreator = new TransformerFactoryCreatorImpl();
    ProblemReporter problemReporter = new ProblemReporterTestImpl();
    
    BatchConverter converter = new BatchConverterImpl(problemReporter, new StatusReporterImpl(), new ConverterStatusReporterTestImpl(),
        new ConvertorWorkerInteractorTestImpl() , transformerCreator);

    final List<File> inputFiles = new ArrayList<File>();
    inputFiles.add(sample);
        
    File convertedFile = ConverterFileUtils.getOutputFile(sample, FileExtensionType.YAML_OUTPUT_EXTENSION , outputFolder);
    
    try {
      converter.convertFiles(ConverterTypes.JSON_TO_YAML, new UserInputsProvider() {
        @Override
        public boolean mustOpenConvertedFiles() {
          return false;
        }
        @Override
        public File getOutputFolder() {
          return outputFolder;
        }
        @Override
        public List<File> getInputFiles() {
          return inputFiles;
        }
        @Override
        public Boolean getAdditionalOptionValue(String additionalOptionId) {
          return null;
        }
        @Override
        public Integer getMaxHeadingLevelForCreatingTopics() {
          return 1;
        }
      });
      assertTrue(FileComparationUtil.compareLineToLine(goodSample, convertedFile));

    } finally {
      FileComparationUtil.deleteRecursivelly(outputFolder);    
    }
  }
  
  /**
   * <p><b>Description:</b> Test line width option from oxygen affects converted YAML files.</p>
   * <p><b>Bug ID:</b> EXM-51942</p>
   *
   * @author artur_bozieac
   *
   * @throws Exception
   */
  @Test
  public void testEXM51942() throws TransformerException, IOException {
    int oldOption = Options.getInstance().getIntegerProperty(APIAccessibleOptionTags.EDITOR_LINE_WIDTH);
    Options.getInstance().setIntegerProperty(APIAccessibleOptionTags.EDITOR_LINE_WIDTH, 40);
    
    File sample  = new File("test-sample/EXM-51942/form-controls.json");   
    File goodSample = new File("test-sample/EXM-51942/form-controls.yaml");
    final File outputFolder = new File(sample.getParentFile(), "out");
    
    TransformerFactoryCreator transformerCreator = new TransformerFactoryCreatorImpl();
    ProblemReporter problemReporter = new ProblemReporterTestImpl();
    
    BatchConverter converter = new BatchConverterImpl(problemReporter, new StatusReporterImpl(), new ConverterStatusReporterTestImpl(),
        new ConvertorWorkerInteractorTestImpl() , transformerCreator);

    final List<File> inputFiles = new ArrayList<File>();
    inputFiles.add(sample);
        
    File convertedFile = ConverterFileUtils.getOutputFile(sample, FileExtensionType.YAML_OUTPUT_EXTENSION , outputFolder);
    
    try {
      converter.convertFiles(ConverterTypes.JSON_TO_YAML, new UserInputsProvider() {
        @Override
        public boolean mustOpenConvertedFiles() {
          return false;
        }
        @Override
        public File getOutputFolder() {
          return outputFolder;
        }
        @Override
        public List<File> getInputFiles() {
          return inputFiles;
        }
        @Override
        public Boolean getAdditionalOptionValue(String additionalOptionId) {
          return null;
        }
        @Override
        public Integer getMaxHeadingLevelForCreatingTopics() {
          return 1;
        }
      });
      
      // To see the contents
      // assertEquals(new String(Files.readAllBytes(Paths.get(goodSample.getAbsolutePath())), Charsets.UTF_8),
      //              new String(Files.readAllBytes(Paths.get(convertedFile.getAbsolutePath())), Charsets.UTF_8));
      assertTrue(FileComparationUtil.compareLineToLine(goodSample, convertedFile));

    } finally {
      FileComparationUtil.deleteRecursivelly(outputFolder);  
      Options.getInstance().setIntegerProperty(APIAccessibleOptionTags.EDITOR_LINE_WIDTH, oldOption);
    }
  }
}
