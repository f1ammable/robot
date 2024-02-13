# Command and Control script as platform agnostic replacement for ev3control

from pathlib import Path
from sys import platform
import os
import subprocess
from typing import List # I like typed languages
import shutil

def make_cp(path: Path) -> str:
    sep: str = None
    if not Path(path).exists():
        print("Invalid dir")
        exit(2)
    
    jars: List[Path] = list(Path(path).rglob("*.jar"))
    if platform != "win32":
        sep = ":"
    else:
        sep = ";"

    return sep.join(str(jar) for jar in jars)

def main() -> None:
    ev3_cmd: str = "ev3control"
    ev3_home: str = str(Path(__file__).parent / "tools")
    ev3_classpath: str = make_cp(Path(ev3_home))

    if "JAVA_HOME" not in os.environ:
        print("JAVA_HOME is not set")
        exit(1)

    if shutil.which("java") is not None:
        java_cmd: List[str] = ["java", f"-DEV3.home={ev3_home}", f"-DCOMMAND_NAME={ev3_cmd}", "-classpath", ev3_classpath, "lejos.ev3.tools.EV3Control"]
        subprocess.run(java_cmd)
    else:
        print("Java executable not found")
        exit(3)
    
if __name__ == "__main__":
    main()
