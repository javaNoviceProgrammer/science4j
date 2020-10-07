package org.gsl4j.plot.util;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.gsl4j.util.StringUtils;

public class PyCommand {

    private final PythonConfig pythonConfig;

    public PyCommand(PythonConfig pythonConfig) {
        this.pythonConfig = pythonConfig;
    }

    private final static Pattern ERROR_PAT = Pattern.compile("^.+Error:");

    private List<String> buildCommandArgs(String scriptPath) {
        StringBuilder shell = new StringBuilder();
        if (!StringUtils.isNullOrEmpty(pythonConfig.getPyenv())) {
            shell.append("pyenv shell ").append(pythonConfig.getPyenv()).append("; ");

            if (!StringUtils.isNullOrEmpty(pythonConfig.getVirtualenv())) {
                shell.append("export PYENV_VIRTUALENV_DISABLE_PROMPT=1; ");
                shell.append("pyenv activate ").append(pythonConfig.getVirtualenv()).append("; ");
            }
            shell.append("python ").append(scriptPath);
        }

        List<String> com;
        if (!StringUtils.isNullOrEmpty(pythonConfig.getPythonBinPath())) {
            com = new ArrayList<String>() ;
            com.add(pythonConfig.getPythonBinPath()) ;
            com.add(scriptPath) ;
        } else if (shell.length() != 0) {
            // -l: Use login shell
            com = new ArrayList<String>() ;
            com.add("bash") ;
            com.add("-l") ;
            com.add("-c") ;
            com.add(shell.toString()) ;
        } else {
            // system's default
        		com = new ArrayList<String>() ;
        		com.add("python") ;
        		com.add(scriptPath) ;
        }

        return com;
    }

    private void command(List<String> commands) throws IOException, PythonExecutionException {
        ProcessBuilder pb = new ProcessBuilder(commands);
        Process process = pb.start();

        // stdout
        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line = br.readLine();
        while (line != null) {
            System.out.println(line);
            line = br.readLine();
        }

        br = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        StringBuilder sb = new StringBuilder();
        line = br.readLine();
        boolean hasError = false;
        while (line != null) {
            sb.append(line).append('\n');
            Matcher matcher = ERROR_PAT.matcher(line);
            if (matcher.find()) {
                hasError = true;
            }
            line = br.readLine();
        }

        String msg = sb.toString();
        if (hasError) {
            throw new PythonExecutionException("Python execution error: " + msg);
        } else {

        }
    }

    private void writeFile(String pythonScript, File script) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(script), StandardCharsets.UTF_8));
        bw.write(pythonScript);
        bw.close();
    }

    public void execute(String pythonScript) throws IOException, PythonExecutionException {
        File tmpDir = new File(".") ;
        tmpDir.deleteOnExit();
        File script = new File(tmpDir, "exec.py");

        writeFile(pythonScript, script);

        String scriptPath = Paths.get(script.toURI()).toAbsolutePath().toString();
        command(buildCommandArgs(scriptPath));
        tmpDir.delete();
    }
}
