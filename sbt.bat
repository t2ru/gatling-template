@echo off
set RUNDIR=%~dp0
java -cp %RUNDIR%\sbt-launch-0.13.9.jar xsbt.boot.Boot %*
