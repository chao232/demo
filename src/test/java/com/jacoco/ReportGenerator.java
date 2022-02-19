package com.jacoco;


import org.jacoco.core.analysis.*;
import org.jacoco.core.data.ExecutionData;
import org.jacoco.core.data.ExecutionDataStore;
import org.jacoco.core.tools.ExecFileLoader;
import org.jacoco.report.DirectorySourceFileLocator;
import org.jacoco.report.FileMultiReportOutput;
import org.jacoco.report.IReportVisitor;
import org.jacoco.report.html.HTMLFormatter;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

public class ReportGenerator {
    private final String title;

    private final File executionDataFile;
    private final File classesDirectory;
    private final File sourceDirectory;
    private final File reportDirectory;

    private ExecFileLoader execFileLoader;

    /**
     * Create a new generator based for the given project.
     *
     * @param projectDirectory
     */
    public ReportGenerator(final File projectDirectory) {
        this.title = projectDirectory.getName();
        this.executionDataFile = new File(projectDirectory, "jacoco-client-assets-2.exec");//覆盖率的exec文件地址
        this.classesDirectory = new File(projectDirectory, "target");//目录下必须包含源码编译过的class文件,用来统计覆盖率。所以这里用server打出的jar包地址即可

        this.sourceDirectory = new File(projectDirectory, "/src/main/java");//源码的/src/main/java,只有写了源码地址覆盖率报告才能打开到代码层。使用jar只有数据结果
        this.reportDirectory = new File(projectDirectory, "coveragereport");//要保存报告的地址
    }

    /**
     * Create the report.
     *
     * @throws IOException
     */
    public void create() throws IOException {

        // Read the jacoco.exec file. Multiple data files could be merged
        // at this point
        loadExecutionData();

        // Run the structure analyzer on a single class folder to build up
        // the coverage model. The process would be similar if your classes
        // were in a jar file. Typically you would create a bundle for each
        // class folder and each jar you want in your report. If you have
        // more than one bundle you will need to add a grouping node to your
        // report
        final IBundleCoverage bundleCoverage = analyzeStructure();

        createReport(bundleCoverage);

    }

    private void createReport(final IBundleCoverage bundleCoverage)
            throws IOException {

        // Create a concrete report visitor based on some supplied
        // configuration. In this case we use the defaults
        final HTMLFormatter htmlFormatter = new HTMLFormatter();
        final IReportVisitor visitor = htmlFormatter
                .createVisitor(new FileMultiReportOutput(reportDirectory));

        // Initialize the report with all of the execution and session
        // information. At this point the report doesn't know about the
        // structure of the report being created
        visitor.visitInfo(execFileLoader.getSessionInfoStore().getInfos(),
                execFileLoader.getExecutionDataStore().getContents());
        // Populate the report structure with the bundle coverage information.
        // Call visitGroup if you need groups in your report.
        visitor.visitBundle(bundleCoverage, new DirectorySourceFileLocator(
                sourceDirectory, "utf-8", 4));

        // Signal end of structure information to allow report to write all
        // information out
        visitor.visitEnd();

    }

    private void loadExecutionData() throws IOException {
        execFileLoader = new ExecFileLoader();
        execFileLoader.load(executionDataFile);
        ExecutionDataStore executionDataStore = execFileLoader.getExecutionDataStore();
        Collection<ExecutionData> contents = executionDataStore.getContents();
        for (ExecutionData content : contents) {
            if(content.getName().equals("com/tech/geely/assets/service/VehicleService")){
//                if(content.getName().equals("com/tech/geely/user/service/DriverService")){
                boolean[] probes = content.getProbes();
                int i=0;
                for (boolean probe : probes) {
                   if(probe){
                       i++;
                   }
                }
                System.out.println("已执行："+i);

            }
        }

    }

    private IBundleCoverage analyzeStructure() throws IOException {
        final CoverageBuilder coverageBuilder = new CoverageBuilder();
        final Analyzer analyzer = new Analyzer(
                execFileLoader.getExecutionDataStore(), coverageBuilder);

        analyzer.analyzeAll(classesDirectory);
        Collection<ISourceFileCoverage> sourceFiles = coverageBuilder.getSourceFiles();
        for (ISourceFileCoverage sourceFile : sourceFiles) {
            if(sourceFile.getName().equals("VehicleBaseController.java")){
                ICounter classCounter = sourceFile.getClassCounter();
            }
        }


        return coverageBuilder.getBundle(title);
    }
}