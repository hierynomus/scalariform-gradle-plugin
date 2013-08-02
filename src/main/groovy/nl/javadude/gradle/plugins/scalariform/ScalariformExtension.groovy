package nl.javadude.gradle.plugins.scalariform

import scalariform.formatter.preferences.*

class ScalariformExtension {
  def prefs = FormattingPreferences$.newInstance().apply()

  def setAlignParameters(boolean v) {
    prefs = prefs.setPreference(AlignParameters$.newInstance(), v)
  }

  def alignSingleLineCaseStatements(boolean v) {
    prefs = prefs.setPreference(AlignSingleLineCaseStatements$.newInstance(), v)
  }

  def setAlignSingleLineCaseStatements_maxArrowIndent(int i) {
    prefs = prefs.setPreference(AlignSingleLineCaseStatements.MaxArrowIndent$.newInstance(), i)
  }

  def setCompactControlReadability(boolean v) {
    prefs = prefs.setPreference(CompactControlReadability$.newInstance(), v)
  }

  def setCompactStringConcatenation(boolean v) {
    prefs = prefs.setPreference(CompactStringConcatenation$.newInstance(), v)
  }

  def setDoubleIndentClassDeclaration(boolean v) {
    prefs = prefs.setPreference(DoubleIndentClassDeclaration$.newInstance(), v)
  }

  def setFormatXml(boolean v) {
    prefs = prefs.setPreference(FormatXml$.newInstance(), v)
  }

  def setIndentLocalDefs(boolean v) {
    prefs = prefs.setPreference(IndentLocalDefs$.newInstance(), v)
  }

  def setIndentPackageBlocks(boolean v) {
    prefs = prefs.setPreference(IndentPackageBlocks$.newInstance(), v)
  }

  def setIndentSpaces(int i) {
    prefs = prefs.setPreference(IndentSpaces$.newInstance(), i)
  }

  def setIndentWithTabs(boolean v) {
    prefs = prefs.setPreference(IndentWithTabs$.newInstance(), v)
  }

  def setMultilineScaladocCommentsStartOnFirstLine(boolean v) {
    prefs = prefs.setPreference(MultilineScaladocCommentsStartOnFirstLine$.newInstance(), v)
  }

  def setPreserveDanglingCloseParenthesis(boolean v) {
    prefs = prefs.setPreference(PreserveDanglingCloseParenthesis$.newInstance(), v)
  }

  def setPlaceScaladocAsterisksBeneathSecondAsterisk(boolean v) {
    prefs = prefs.setPreference(PlaceScaladocAsterisksBeneathSecondAsterisk$.newInstance(), v)
  }

  def setPreserveSpaceBeforeArguments(boolean v) {
    prefs = prefs.setPreference(PreserveSpaceBeforeArguments$.newInstance(), v)
  }

  def setRewriteArrowSymbols(boolean v) {
    prefs = prefs.setPreference(RewriteArrowSymbols$.newInstance(), v)
  }

  def setSpaceBeforeColon(boolean v) {
    prefs = prefs.setPreference(SpaceBeforeColon$.newInstance(), v)
  }

  def setSpaceInsideBrackets(boolean v) {
    prefs = prefs.setPreference(SpaceInsideBrackets$.newInstance(), v)
  }

  def setSpaceInsideParentheses(boolean v) {
    prefs = prefs.setPreference(SpaceInsideParentheses$.newInstance(), v)
  }

  def setSpacesWithinPatternBinders(boolean v) {
    prefs = prefs.setPreference(SpacesWithinPatternBinders$.newInstance(), v)
  }
}
