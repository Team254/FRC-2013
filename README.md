# Team 254's 2013 Robot Code

This repository contains the Team 254 2013 FRC codebase. This code is released under the BSD 2-clause license. A copy of this license is included in COPYING.

Unlike previous years, we used Java instead of C++, and were very happy with the results. Our members learn Java in class, and more students were ultimately able to contribute to the codebase. It also meant a simpler, OS-independent build process that made development easier.

## Contents

This year's codebase can be opened and conveniently browsed in the NetBeans IDE. Code lives in the src/ directory and is organized in directories based on functionality and Java package.

Looking for a place to start? Take a look at [**com.team254.frc2013.Overkill**](https://github.com/Team254/FRC-2013/blob/master/src/com/team254/frc2013/Overkill.java).

### com.team254.frc2013
 * **Constants.java** contains constants correspondong to ports, PID values, and shooter speed. These can be accessed, but not modified, by the rest of the codebase.
 * **Overkill.java** subclasses the IterativeRobot template in order to inherit periodic and disabled states. It handles logic for disabled, autonomous, and tele-operated states.

### com.team254.frc2013.auto
Contains autonomous routines that can run commands sequentially or in parallel. This was previously handled by uploading scripts to the robot via FTP and parsing their commands. com.team254.frc2013.AutoModeSelector allows the driver to cycle through autonomous modes before executing one.

### com.team254.frc2013.commands
Representations of individual tasks the robot can perform. These tie into the CommandBasedRobot paradigm.

 * **CommandBase.java** Contains a single instance of all subsystems in order to send them commands without creating additional instances.

### com.team254.frc2013.subsystems
Representations of aspects of the robot with methods for retrieving source values and checking / setting the state of the subsystem.

### com.team254.frc2013.subsystems.controllers
State machines and state space controllers that represent the intake wrist and shooter flywheel.

### com.team254.lib.control
Contains infrastructure for controlling all subsystems of the robot.

 * **ControlOutput.java** Result of calculations performed by the controller on the ControlSource.
 * **ControlSource.java** Data collected by sensors in a subsystem.
 * **ControlUpdater.java** Runs in its own thread and periodically checks up on all subsystems using their ControlSource to affect the ControlOutput.

### com.team254.control.impl
Implementations of the motion profiles and controllers found in com.team254.lib.control.

### com.team254.lib.util
Miscellaneous utility classes.

## Links

Be sure to check out the **[wiki page](https://github.com/Team254/FRC-2013/wiki)** for guides and tutorials to get  started.

Please note that all code committed to this repository should conform to the [Team 254 Java Style Guide](https://docs.google.com/document/d/1gMsSANhnIE8b1mUcJgBJa87dqTzh7N4hOPB8679nu7E/edit).

[JAVA ME Documentation](http://docs.oracle.com/javame/config/cldc/ref-impl/cldc1.1/jsr139/index.html)
