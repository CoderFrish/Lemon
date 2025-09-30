prop() {
  grep "^[[:space:]]*${1}" gradle.properties | cut -d'=' -f2 | sed 's/^[[:space:]]*//; s/\r//'
}

mcversion=$(prop mcVersion)
version=$(prop version)
commitid=$(git log --pretty='%h' -1)
build_date=$(date -u +'%Y-%m-%d %H:%M:%S UTC')

git config --global user.email "no-reply@github.com"
git config --global user.name "Github Actions"
chmod +x gradlew

./gradlew applyAllPatches
./gradlew createMojmapPaperclipJar

echo "version=$version" >> $GITHUB_ENV
echo "mcversion=$mcversion" >> $GITHUB_ENV
echo "commit_id=$commitid" >> $GITHUB_ENV
echo "build_date=$build_date" >> $GITHUB_ENV
echo "commit_msg=$(git log --pretty='> [%h] %s' -1)" >> $GITHUB_ENV

mv traium-server/build/libs/traium-paperclip-$version-mojmap.jar traium-server/build/libs/Traium-$version.jar
